package com.riskaware.arbitrage.core.orchestration.ports;

import java.util.List;

/**
 * Tuvastab arbitraaživõimalused hinnainfo snapshot’i põhjal.
 *
 * @param <TPriceSnapshot> hinnainfo andmetüübi abstraktsioon
 * @param <TOpportunity>   tuvastatud võimaluse andmetüüp
 */
public interface OpportunityDetectionPort<TPriceSnapshot, TOpportunity> {

    /**
     * Analüüsib sisend-snapshot’i ja tagastab
     * leitud arbitraaživõimaluste loendi.
     *
     * @param snapshot turu/hinna hetkeseis
     * @return tuvastatud võimaluste nimekiri (võib olla tühi)
     */
    List<TOpportunity> detect(TPriceSnapshot snapshot);
}