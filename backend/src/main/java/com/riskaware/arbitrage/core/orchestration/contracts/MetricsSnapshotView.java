package com.riskaware.arbitrage.core.orchestration.contracts;

/**
 * Minimaalne analüütika hetkeseisu (snapshot) vaade.
 */
public interface MetricsSnapshotView {

    /**
     * Inimloetav kokkuvõte analüütika,
     * kasutajaliidese ja logide jaoks.
     */
    String getSummary();
}