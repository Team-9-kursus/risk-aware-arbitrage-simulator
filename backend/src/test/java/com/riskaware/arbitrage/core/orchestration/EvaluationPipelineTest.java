package com.riskaware.arbitrage.core.orchestration;

import com.riskaware.arbitrage.core.decision.DecisionEngine;
import com.riskaware.arbitrage.core.decision.DecisionPolicy;
import com.riskaware.arbitrage.core.orchestration.ports.ReserveEvaluationPort;
import com.riskaware.arbitrage.core.orchestration.ports.RiskEvaluationPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class EvaluationPipelineTest {

    @Test
    void returnsGoWhenRiskAndReserveArePresent() {
        RiskEvaluationPort<String> riskEvaluation = opportunity -> new Object();
        ReserveEvaluationPort<String> reserveEvaluation = (opportunity, riskReport) -> new Object();
        DecisionEngine decisionEngine = new DecisionEngine(DecisionPolicy.defaults());

        EvaluationPipeline<String> pipeline =
                new EvaluationPipeline<>(riskEvaluation, reserveEvaluation, decisionEngine);

        EvaluationResult result = pipeline.run("opportunity-1");

        assertNotNull(result);
        assertNotNull(result.getDecision());
        assertTrue(result.getDecision().isGo());
    }

    @Test
    void returnsNoGoWhenRiskReportIsMissing() {
        RiskEvaluationPort<String> riskEvaluation = opportunity -> null;
        ReserveEvaluationPort<String> reserveEvaluation = (opportunity, riskReport) -> new Object();
        DecisionEngine decisionEngine = new DecisionEngine(DecisionPolicy.defaults());

        EvaluationPipeline<String> pipeline =
                new EvaluationPipeline<>(riskEvaluation, reserveEvaluation, decisionEngine);

        EvaluationResult result = pipeline.run("opportunity-1");

        assertNotNull(result);
        assertNotNull(result.getDecision());
        assertFalse(result.getDecision().isGo());
    }
}