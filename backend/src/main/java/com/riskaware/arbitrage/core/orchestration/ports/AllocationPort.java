package com.riskaware.arbitrage.core.orchestration.ports;

import com.riskaware.arbitrage.core.orchestration.contracts.AllocationView;
import com.riskaware.arbitrage.core.orchestration.contracts.RiskReportView;
import com.riskaware.arbitrage.core.orchestration.contracts.ReserveStateView;

import java.util.List;

/**
 * Otsustab simuleeritud kapitali jaotuse
 * tuvastatud võimaluste vahel.
 *
 * @param <TOpportunity> võimaluse andmetüübi abstraktsioon
 */
public interface AllocationPort<TOpportunity> {

    /**
     * Arvutab jaotuse, võttes arvesse:
     * - tuvastatud võimalusi
     * - riskiraporti tulemusi
     * - reservi- ja ekspositsioonipiiranguid
     *
     * @param opportunities tuvastatud arbitraaživõimaluste loend
     * @param riskReport    riskiraporti vaade
     * @param reserveState  reserviseisundi vaade
     * @return jaotuse vaade (sisaldab teostatavuse infot ja kokkuvõtet)
     */
    AllocationView allocate(List<TOpportunity> opportunities,
                            RiskReportView riskReport,
                            ReserveStateView reserveState);
}