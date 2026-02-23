package com.riskaware.arbitrage.core.orchestration.contracts;

/**
 * Minimaalne vaade reservipiirangute kontrollimiseks.
 */
public interface ReserveStateView {

    /**
     * Kui true, siis reservi / ekspositsiooni piirang on ületatud
     * ja poliitika alusel tuleb tegevus blokeerida.
     */
    boolean isLimitExceeded();

    /**
     * Inimloetav kokkuvõte kasutajaliidese,
     * silumise (debug) ja logide jaoks.
     */
    String getSummary();
}