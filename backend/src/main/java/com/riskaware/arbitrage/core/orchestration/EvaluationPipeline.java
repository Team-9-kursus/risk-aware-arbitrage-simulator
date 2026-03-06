package com.riskaware.arbitrage.core.orchestration;

import com.riskaware.arbitrage.core.decision.DecisionEngine;
import com.riskaware.arbitrage.core.decision.DecisionResult;
import com.riskaware.arbitrage.core.orchestration.ports.ReserveEvaluationPort;
import com.riskaware.arbitrage.core.orchestration.ports.RiskEvaluationPort;

import java.util.Objects;

/**
 * Minimal M1 evaluation pipeline.
 *
 * Flow:
 * risk -> reserve -> decision
 */
public final class EvaluationPipeline<TOpportunity> {

    private final RiskEvaluationPort<TOpportunity> riskEvaluation;
    private final ReserveEvaluationPort<TOpportunity> reserveEvaluation;
    private final DecisionEngine decisionEngine;

    public EvaluationPipeline(RiskEvaluationPort<TOpportunity> riskEvaluation,
                              ReserveEvaluationPort<TOpportunity> reserveEvaluation,
                              DecisionEngine decisionEngine) {
        this.riskEvaluation = Objects.requireNonNull(riskEvaluation, "riskEvaluation");
        this.reserveEvaluation = Objects.requireNonNull(reserveEvaluation, "reserveEvaluation");
        this.decisionEngine = Objects.requireNonNull(decisionEngine, "decisionEngine");
    }

    public EvaluationResult run(TOpportunity opportunity) {
        Object riskReport = riskEvaluation.evaluate(opportunity);
        Object reserveState = reserveEvaluation.evaluate(opportunity, riskReport);
        DecisionResult decision = decisionEngine.decide(riskReport, reserveState);

        return new EvaluationResult(decision);
    }
}