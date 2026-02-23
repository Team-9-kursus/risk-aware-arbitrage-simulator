package com.riskaware.arbitrage.core.orchestration.ports;

import com.riskaware.arbitrage.core.orchestration.contracts.MetricsSnapshotView;
import com.riskaware.arbitrage.core.orchestration.contracts.SimulationResultView;

/**
 * Valikuline analüütikamoodul simulatsiooni tulemuste analüüsimiseks.
 */
public interface AnalyticsPort {

    /**
     * Analüüsib simulatsiooni tulemust ja koostab
     * analüütika hetkeseisu (metrics snapshot).
     *
     * @param simulationResult simulatsiooni tulemuse vaade
     * @return analüütika hetkeseisu vaade
     */
    MetricsSnapshotView analyze(SimulationResultView simulationResult);
}