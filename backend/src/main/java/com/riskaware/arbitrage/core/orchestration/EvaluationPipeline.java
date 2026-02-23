package com.riskaware.arbitrage.core.orchestration;

import com.riskaware.arbitrage.core.decision.DecisionEngine;
import com.riskaware.arbitrage.core.decision.DecisionResult;
import com.riskaware.arbitrage.core.orchestration.contracts.AllocationView;
import com.riskaware.arbitrage.core.orchestration.contracts.MetricsSnapshotView;
import com.riskaware.arbitrage.core.orchestration.contracts.RiskReportView;
import com.riskaware.arbitrage.core.orchestration.contracts.ReserveStateView;
import com.riskaware.arbitrage.core.orchestration.contracts.SimulationResultView;
import com.riskaware.arbitrage.core.orchestration.ports.*;

import java.util.List;
import java.util.Objects;

/**
 * Backendi tuumloogika üks sisenemispunkt.
 *
 * Töötlusahela järjekord:
 * Ingestion --> Opportunity --> Risk --> Reserve --> Allocation --> Decision --> (GO) Simulation --> Analytics
 *
 * OLULINE:
 * - Kutsungite järjekord on deterministlik
 * - Kui otsus on NO-GO, ei tohi SimulationPort'i kunagi kutsuda
 */
public final class EvaluationPipeline<TPriceSnapshot, TOpportunity> {

    /**
     * Turusisendi (snapshot) hankimine.
     */
    private final IngestionPort<TPriceSnapshot> ingestion;

    /**
     * Arbitraaživõimaluste tuvastamine.
     */
    private final OpportunityDetectionPort<TPriceSnapshot, TOpportunity> opportunityDetection;

    /**
     * Riskihindamine.
     */
    private final RiskEvaluationPort<TOpportunity> riskEvaluation;

    /**
     * Reservi- ja ekspositsioonipiirangute hindamine.
     */
    private final ReserveEvaluationPort<TOpportunity> reserveEvaluation;

    /**
     * Kapitali jaotuse otsustamine.
     */
    private final AllocationPort<TOpportunity> allocation;

    /**
     * GO/NO-GO otsustusmootor.
     */
    private final DecisionEngine decisionEngine;

    /**
     * Simulatsioonimootor.
     */
    private final SimulationPort<TOpportunity> simulation;

    /**
     * Analüütikamoodul (valikuline).
     */
    private final AnalyticsPort analytics; // võib olla null

    /**
     * Konstruktor – kõik kriitilised sõltuvused on kohustuslikud.
     */
    public EvaluationPipeline(IngestionPort<TPriceSnapshot> ingestion,
                              OpportunityDetectionPort<TPriceSnapshot, TOpportunity> opportunityDetection,
                              RiskEvaluationPort<TOpportunity> riskEvaluation,
                              ReserveEvaluationPort<TOpportunity> reserveEvaluation,
                              AllocationPort<TOpportunity> allocation,
                              DecisionEngine decisionEngine,
                              SimulationPort<TOpportunity> simulation,
                              AnalyticsPort analytics) {

        this.ingestion = Objects.requireNonNull(ingestion, "ingestion");
        this.opportunityDetection = Objects.requireNonNull(opportunityDetection, "opportunityDetection");
        this.riskEvaluation = Objects.requireNonNull(riskEvaluation, "riskEvaluation");
        this.reserveEvaluation = Objects.requireNonNull(reserveEvaluation, "reserveEvaluation");
        this.allocation = Objects.requireNonNull(allocation, "allocation");
        this.decisionEngine = Objects.requireNonNull(decisionEngine, "decisionEngine");
        this.simulation = Objects.requireNonNull(simulation, "simulation");
        this.analytics = analytics; // võib olla null
    }

    /**
     * Käivitab kogu töötlusahela algusest lõpuni.
     *
     * @return EvaluationResult – sisaldab alati otsust,
     *         simulatsiooni ja analüütikat ainult juhul, kui otsus on GO.
     */
    public EvaluationResult run() {

        // 1) Ingestion – turu/hinna hetkeseisu hankimine
        TPriceSnapshot snapshot = ingestion.fetchSnapshot();

        // 2) Võimaluste tuvastamine
        List<TOpportunity> opportunities = opportunityDetection.detect(snapshot);
        boolean hasOpps = opportunities != null && !opportunities.isEmpty();
        if (opportunities == null) opportunities = List.of();

        // 3) Riskihindamine
        RiskReportView riskReport = riskEvaluation.evaluate(opportunities);

        // 4) Reservi- ja ekspositsioonipiirangute hindamine
        ReserveStateView reserveState = reserveEvaluation.evaluate(opportunities, riskReport);

        // 5) Jaotuse arvutamine
        AllocationView allocationView = allocation.allocate(opportunities, riskReport, reserveState);

        // 6) Otsus (GO/NO-GO värav)
        DecisionResult decision =
                decisionEngine.decide(hasOpps, riskReport, reserveState, allocationView);

        // 7) Kui otsus on NO-GO, lõpetame töötluse siin
        if (!decision.isGo()) {
            return new EvaluationResult(decision, null, null);
        }

        // 8) Simulatsiooni käivitamine (ainult GO korral)
        SimulationResultView sim =
                simulation.simulate(opportunities, allocationView);

        // 9) Valikuline analüütika
        MetricsSnapshotView metrics = null;
        if (analytics != null && sim != null) {
            metrics = analytics.analyze(sim);
        }

        return new EvaluationResult(decision, sim, metrics);
    }
}