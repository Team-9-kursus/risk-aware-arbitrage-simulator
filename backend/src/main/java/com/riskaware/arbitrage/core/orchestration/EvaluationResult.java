package com.riskaware.arbitrage.core.orchestration;

import com.riskaware.arbitrage.core.decision.DecisionResult;

/**
 * Minimal result returned by the evaluation pipeline.
 */
public final class EvaluationResult {

    private final DecisionResult decision;

    public EvaluationResult(DecisionResult decision) {
        this.decision = decision;
    }

    public DecisionResult getDecision() {
        return decision;
    }
}