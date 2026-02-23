package com.riskaware.arbitrage.core.orchestration.contracts;

/**
 * Minimaalne vaade jaotuse (allocation) teostatavuse hindamiseks.
 */
public interface AllocationView {

    /**
     * Kui false, siis jaotus ei ole teostatav
     * ning poliitika alusel tuleb tegevus blokeerida.
     */
    boolean isFeasible();

    /**
     * Inimloetav kokkuvõte kasutajaliidese,
     * silumise (debug) ja logide jaoks.
     */
    String getSummary();
}