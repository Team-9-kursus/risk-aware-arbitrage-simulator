package com.riskaware.arbitrage.core.decision;

import java.util.EnumSet;
import java.util.Set;

/**
 * Minimal M1 decision engine.
 *
 * Decision is based on risk and reserve inputs only.
 */
public final class DecisionEngine {

    private final DecisionPolicy policy;

    public DecisionEngine(DecisionPolicy policy) {
        this.policy = policy == null ? DecisionPolicy.defaults() : policy;
    }

    public DecisionPolicy getPolicy() {
        return policy;
    }

    public DecisionResult decide(Object riskReport, Object reserveState) {
        Set<DecisionReason> reasons = EnumSet.noneOf(DecisionReason.class);

        if (riskReport == null) {
            reasons.add(DecisionReason.RISK_REPORT_MISSING);
        }

        if (reserveState == null) {
            reasons.add(DecisionReason.RESERVE_STATE_MISSING);
        }

        boolean go = reasons.isEmpty();

        String explain = go
                ? "GO: minimal M1 checks passed."
                : "NO-GO: " + reasons;

        DecisionResult.Builder b = DecisionResult.builder()
                .go(go)
                .explain(explain)
                .riskScore(null)
                .reserveLimitExceeded(null)
                .allocationFeasible(null);

        for (DecisionReason r : reasons) {
            b.addReason(r);
        }

        return b.build();
    }
}