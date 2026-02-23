package com.riskaware.arbitrage.core.orchestration.ports;

import com.riskaware.arbitrage.core.orchestration.contracts.AllocationView;
import com.riskaware.arbitrage.core.orchestration.contracts.SimulationResultView;

import java.util.List;

/**
 * Käivitab simulatsiooni, kasutades jaotust ja tuvastatud võimalusi.
 * EI TOHI kutsuda, kui Decision tulemus on NO-GO.
 *
 * @param <TOpportunity> võimaluse andmetüübi abstraktsioon
 */
public interface SimulationPort<TOpportunity> {

    /**
     * Teostab simulatsiooni vastavalt:
     * - tuvastatud võimalustele
     * - arvutatud jaotusele
     *
     * @param opportunities tuvastatud arbitraaživõimaluste loend
     * @param allocation    jaotuse vaade
     * @return simulatsiooni tulemuse vaade
     */
    SimulationResultView simulate(List<TOpportunity> opportunities,
                                   AllocationView allocation);
}