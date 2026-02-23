package com.riskaware.arbitrage.core.orchestration.contracts;

/**
 * Minimaalne vaade, mida vajab DecisionEngine
 * ning mida kasutatakse API-s selgituste kuvamiseks.
 * Teised moodulid võivad selle realiseerida või adapteerida.
 */
public interface RiskReportView {

    /**
     * Normaliseeritud riskiskoor vahemikus [0..1]
     * (0 = madal risk, 1 = kõrge risk).
     */
    Double getRiskScore();

    /**
     * Inimloetav kokkuvõte kasutajaliidese,
     * silumise (debug) ja logide jaoks.
     */
    String getSummary();
}