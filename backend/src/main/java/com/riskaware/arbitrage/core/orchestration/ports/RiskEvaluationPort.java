package com.riskaware.arbitrage.core.orchestration.ports;

import com.riskaware.arbitrage.core.orchestration.contracts.RiskReportView;
import java.util.List;

/**
 * Hindab tuvastatud võimalustega seotud riski.
 *
 * @param <TOpportunity> võimaluse andmetüübi abstraktsioon
 */
public interface RiskEvaluationPort<TOpportunity> {

    /**
     * Arvutab riskitaseme tuvastatud võimaluste põhjal
     * ja tagastab riskiraporti vaate.
     *
     * @param opportunities tuvastatud arbitraaživõimaluste loend
     * @return riskiraporti vaade (sisaldab riskiskoori ja kokkuvõtet)
     */
    RiskReportView evaluate(List<TOpportunity> opportunities);
}