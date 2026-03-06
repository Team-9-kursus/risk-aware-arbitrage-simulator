package com.riskaware.arbitrage.core.decision;

/**
 * Policy thresholds used by the decision engine.
 */
public final class DecisionPolicy {

    /** Maximum allowed risk score (0..1). */
    private final double maxRiskScore;

    /** Block decision if reserve limit is exceeded. */
    private final boolean blockOnReserveLimitExceeded;

    /** Block decision if allocation is not feasible. */
    private final boolean blockOnAllocationNotFeasible;

    public DecisionPolicy(double maxRiskScore,
                          boolean blockOnReserveLimitExceeded,
                          boolean blockOnAllocationNotFeasible) {

        if (maxRiskScore < 0.0 || maxRiskScore > 1.0) {
            throw new IllegalArgumentException("maxRiskScore must be between 0 and 1");
        }

        this.maxRiskScore = maxRiskScore;
        this.blockOnReserveLimitExceeded = blockOnReserveLimitExceeded;
        this.blockOnAllocationNotFeasible = blockOnAllocationNotFeasible;
    }

    public double getMaxRiskScore() {
        return maxRiskScore;
    }

    public boolean isBlockOnReserveLimitExceeded() {
        return blockOnReserveLimitExceeded;
    }

    public boolean isBlockOnAllocationNotFeasible() {
        return blockOnAllocationNotFeasible;
    }

    /**
     * Default project policy.
     */
    public static DecisionPolicy defaults() {
        return new DecisionPolicy(
                0.70,
                true,
                true
        );
    }
}