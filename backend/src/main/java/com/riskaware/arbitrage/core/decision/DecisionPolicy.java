package com.riskaware.arbitrage.core.decision;

/**
 * Seadistatavad poliitika läved GO/NO-GO otsuse jaoks.
 * Hoia loogika lihtne ja deterministlik.
 */
public final class DecisionPolicy {

    /**
     * Riskiskoor vahemikus [0..1].
     * Kui väärtus on sellest suurem => NO-GO.
     */
    private final double maxRiskScore;

    /**
     * Kui true, siis blokeeritakse,
     * kui reserve engine ütleb "limit exceeded".
     */
    private final boolean blockOnReserveLimitExceeded;

    /**
     * Kui true, siis blokeeritakse,
     * kui allocation ütleb "not feasible".
     */
    private final boolean blockOnAllocationNotFeasible;

    public DecisionPolicy(double maxRiskScore,
                          boolean blockOnReserveLimitExceeded,
                          boolean blockOnAllocationNotFeasible) {
        if (maxRiskScore < 0.0 || maxRiskScore > 1.0) {
            throw new IllegalArgumentException("maxRiskScore peab olema vahemikus [0..1]");
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
     * Mõistlikud vaikimisi väärtused projekti jaoks.
     */
    public static DecisionPolicy defaults() {
        return new DecisionPolicy(
                0.70,  // maksimaalne lubatud riskiskoor
                true,  // blokeeri, kui reservi limiit on ületatud
                true   // blokeeri, kui jaotus (allocation) ei ole teostatav
        );
    }
}