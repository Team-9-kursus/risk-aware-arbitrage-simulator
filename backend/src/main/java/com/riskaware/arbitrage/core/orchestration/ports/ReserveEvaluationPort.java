package com.riskaware.arbitrage.core.orchestration.ports;

import com.riskaware.arbitrage.core.orchestration.contracts.ReserveStateView;
import com.riskaware.arbitrage.core.orchestration.contracts.RiskReportView;

import java.util.List;

/**
 * Hindab reservi- ja ekspositsioonipiiranguid,
 * arvestades riski ja tuvastatud võimalusi.
 *
 * @param <TOpportunity> võimaluse andmetüübi abstraktsioon
 */
public interface ReserveEvaluationPort<TOpportunity> {

    /**
     * Analüüsib, kas reservi- või ekspositsioonipiirangud
     * on ületatud, võttes arvesse:
     * - tuvastatud võimalusi
     * - riskiraporti tulemusi
     *
     * @param opportunities tuvastatud arbitraaživõimaluste loend
     * @param riskReport    riskiraporti vaade
     * @return reserviseisundi vaade (sisaldab limiidi infot ja kokkuvõtet)
     */
    ReserveStateView evaluate(List<TOpportunity> opportunities, RiskReportView riskReport);
}