package com.riskaware.arbitrage.core.orchestration.contracts;

/**
 * Minimaalne simulatsiooni tulemuse vaade
 * API ja analüütika jaoks.
 */
public interface SimulationResultView {

    /**
     * Inimloetav kokkuvõte kasutajaliidese,
     * analüütika ja logide jaoks.
     */
    String getSummary();
}