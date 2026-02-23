package com.riskaware.arbitrage.core.orchestration;

import com.riskaware.arbitrage.core.decision.DecisionEngine;
import com.riskaware.arbitrage.core.decision.DecisionPolicy;
import com.riskaware.arbitrage.core.orchestration.contracts.AllocationView;
import com.riskaware.arbitrage.core.orchestration.contracts.RiskReportView;
import com.riskaware.arbitrage.core.orchestration.contracts.ReserveStateView;
import com.riskaware.arbitrage.core.orchestration.contracts.SimulationResultView;
import com.riskaware.arbitrage.core.orchestration.ports.*;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EvaluationPipelineTest {

    @Test
    void noGo_blocksSimulation() {
        // Arrange: loo mock-id kõikidele portidele (sõltuvused)
        IngestionPort<String> ingestion = mock(IngestionPort.class);
        OpportunityDetectionPort<String, String> detector = mock(OpportunityDetectionPort.class);
        RiskEvaluationPort<String> riskEval = mock(RiskEvaluationPort.class);
        ReserveEvaluationPort<String> reserveEval = mock(ReserveEvaluationPort.class);
        AllocationPort<String> alloc = mock(AllocationPort.class);
        SimulationPort<String> simulation = mock(SimulationPort.class);
        AnalyticsPort analytics = mock(AnalyticsPort.class);

        // Ingestion tagastab snapshot'i
        when(ingestion.fetchSnapshot()).thenReturn("snapshot");

        // Detector leiab vähemalt ühe võimaluse (et riskivärav üldse relevantne oleks)
        when(detector.detect("snapshot")).thenReturn(List.of("opp1"));

        // Risk liiga kõrge -> eeldame NO-GO (policy max 0.70, siin 0.99)
        RiskReportView riskReport = new RiskReportView() {
            @Override public Double getRiskScore() { return 0.99; }
            @Override public String getSummary() { return "high risk"; }
        };
        when(riskEval.evaluate(anyList())).thenReturn(riskReport);

        // Reserve kontroll OK (ei ületa limiiti)
        ReserveStateView reserveState = new ReserveStateView() {
            @Override public boolean isLimitExceeded() { return false; }
            @Override public String getSummary() { return "ok"; }
        };
        when(reserveEval.evaluate(anyList(), any())).thenReturn(reserveState);

        // Allocation teostatav (feasible)
        AllocationView allocationView = new AllocationView() {
            @Override public boolean isFeasible() { return true; }
            @Override public String getSummary() { return "ok"; }
        };
        when(alloc.allocate(anyList(), any(), any())).thenReturn(allocationView);

        // Otsustusmootor konkreetse poliitikaga (riskilävi 0.70)
        DecisionEngine decisionEngine = new DecisionEngine(new DecisionPolicy(
                0.70, true, true
        ));

        // Koosta pipeline (analytics on siin olemas, aga NO-GO korral ei tohi seda kutsuda)
        EvaluationPipeline<String, String> pipeline = new EvaluationPipeline<>(
                ingestion, detector, riskEval, reserveEval, alloc, decisionEngine, simulation, analytics
        );

        // Act: käivita töötlusahel
        EvaluationResult result = pipeline.run();

        // Assert: tulemus olemas, otsus NO-GO ja simulatsioon puudub
        assertNotNull(result);
        assertFalse(result.getDecision().isGo(), "Eeldatud NO-GO");
        assertTrue(result.getSimulation().isEmpty(), "NO-GO korral peab simulatsioon puuduma");

        // Assert: kontrolli, et simulatsiooni ja analüütikat EI kutsutud
        verify(simulation, never()).simulate(anyList(), any());
        verify(analytics, never()).analyze(any());
    }

    @Test
    void pipelineOrder_isEnforced() {
        // Arrange: loo mock-id portidele, et kontrollida kutsungite järjekorda
        IngestionPort<String> ingestion = mock(IngestionPort.class);
        OpportunityDetectionPort<String, String> detector = mock(OpportunityDetectionPort.class);
        RiskEvaluationPort<String> riskEval = mock(RiskEvaluationPort.class);
        ReserveEvaluationPort<String> reserveEval = mock(ReserveEvaluationPort.class);
        AllocationPort<String> alloc = mock(AllocationPort.class);
        SimulationPort<String> simulation = mock(SimulationPort.class);

        // Ingestion -> snapshot
        when(ingestion.fetchSnapshot()).thenReturn("snapshot");

        // Snapshot -> opportunities
        when(detector.detect("snapshot")).thenReturn(List.of("opp1"));

        // Madal risk -> GO
        RiskReportView riskReport = new RiskReportView() {
            @Override public Double getRiskScore() { return 0.10; }
            @Override public String getSummary() { return "low risk"; }
        };
        when(riskEval.evaluate(anyList())).thenReturn(riskReport);

        // Reserve OK
        ReserveStateView reserveState = new ReserveStateView() {
            @Override public boolean isLimitExceeded() { return false; }
            @Override public String getSummary() { return "ok"; }
        };
        when(reserveEval.evaluate(anyList(), any())).thenReturn(reserveState);

        // Allocation teostatav
        AllocationView allocationView = new AllocationView() {
            @Override public boolean isFeasible() { return true; }
            @Override public String getSummary() { return "ok"; }
        };
        when(alloc.allocate(anyList(), any(), any())).thenReturn(allocationView);

        // Simulatsiooni tulemus (GO korral peab olemas olema)
        SimulationResultView simView = () -> "sim ok";
        when(simulation.simulate(anyList(), any())).thenReturn(simView);

        // Vaikimisi poliitika (school project defaults)
        DecisionEngine decisionEngine = new DecisionEngine(DecisionPolicy.defaults());

        // Pipeline: analytics = null (seda testi ei huvita analüütika)
        EvaluationPipeline<String, String> pipeline = new EvaluationPipeline<>(
                ingestion, detector, riskEval, reserveEval, alloc, decisionEngine, simulation, null
        );

        // Act: käivita töötlusahel
        EvaluationResult result = pipeline.run();

        // Assert: GO ja simulatsioon on olemas
        assertTrue(result.getDecision().isGo(), "Eeldatud GO");
        assertTrue(result.getSimulation().isPresent(), "GO korral peab simulatsioon olemas olema");

        // Assert: kontrolli deterministlik kutsungite järjekord
        InOrder inOrder = inOrder(ingestion, detector, riskEval, reserveEval, alloc, simulation);
        inOrder.verify(ingestion).fetchSnapshot();
        inOrder.verify(detector).detect("snapshot");
        inOrder.verify(riskEval).evaluate(anyList());
        inOrder.verify(reserveEval).evaluate(anyList(), any());
        inOrder.verify(alloc).allocate(anyList(), any(), any());
        inOrder.verify(simulation).simulate(anyList(), any());
        inOrder.verifyNoMoreInteractions();
    }
}