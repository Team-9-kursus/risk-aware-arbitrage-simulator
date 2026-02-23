package com.riskaware.arbitrage.core.orchestration;

import com.riskaware.arbitrage.core.decision.DecisionResult;
import com.riskaware.arbitrage.core.orchestration.contracts.MetricsSnapshotView;
import com.riskaware.arbitrage.core.orchestration.contracts.SimulationResultView;

import java.util.Optional;

/**
 * Töötlusahela lõpptulemus.
 *
 * DecisionResult on alati olemas.
 * Simulatsiooni tulemus ja analüütika on valikulised
 * (olemas ainult siis, kui otsus on GO ja simulatsioon käivitati).
 */
public final class EvaluationResult {

    /**
     * GO/NO-GO otsuse tulemus koos põhjustega.
     */
    private final DecisionResult decision;

    /**
     * Simulatsiooni tulemus (võib olla null,
     * kui otsus oli NO-GO või simulatsiooni ei käivitatud).
     */
    private final SimulationResultView simulation; // nullable

    /**
     * Analüütika hetkeseis (võib olla null,
     * kui analüütikat ei arvutatud).
     */
    private final MetricsSnapshotView metrics;      // nullable

    /**
     * Konstruktor.
     */
    public EvaluationResult(DecisionResult decision,
                            SimulationResultView simulation,
                            MetricsSnapshotView metrics) {
        this.decision = decision;
        this.simulation = simulation;
        this.metrics = metrics;
    }

    /**
     * Tagastab otsuse tulemuse (alati olemas).
     */
    public DecisionResult getDecision() {
        return decision;
    }

    /**
     * Tagastab simulatsiooni tulemuse Optional-na.
     */
    public Optional<SimulationResultView> getSimulation() {
        return Optional.ofNullable(simulation);
    }

    /**
     * Tagastab analüütika hetkeseisu Optional-na.
     */
    public Optional<MetricsSnapshotView> getMetrics() {
        return Optional.ofNullable(metrics);
    }
}