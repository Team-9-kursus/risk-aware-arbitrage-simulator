package com.riskaware.arbitrage.core.orchestration;

import com.riskaware.arbitrage.core.orchestration.contracts.AllocationView;
import com.riskaware.arbitrage.core.orchestration.contracts.RiskReportView;
import com.riskaware.arbitrage.core.orchestration.contracts.ReserveStateView;

import java.util.Collections;
import java.util.List;

/**
 * Töötlusahela (pipeline) kontekst.
 * Puhas andmekandja (data holder) ilma äriloogikata.
 *
 * Sisaldab kõiki vahe- ja lõpptulemusi,
 * mida otsustus- ja simulatsioonimootorid võivad vajada.
 */
public final class EvaluationContext<TPriceSnapshot, TOpportunity> {

    /**
     * Turu/hinna hetkeseis (snapshot).
     */
    private final TPriceSnapshot snapshot;

    /**
     * Tuvastatud arbitraaživõimaluste loend.
     */
    private final List<TOpportunity> opportunities;

    /**
     * Riskiraporti vaade.
     */
    private final RiskReportView riskReport;

    /**
     * Reservi- ja ekspositsiooniseisundi vaade.
     */
    private final ReserveStateView reserveState;

    /**
     * Jaotuse (allocation) vaade.
     */
    private final AllocationView allocation;

    /**
     * Konstruktor.
     * Võimaluste loend muudetakse immutable-kujule,
     * et tagada konteksti muutumatus.
     */
    public EvaluationContext(TPriceSnapshot snapshot,
                             List<TOpportunity> opportunities,
                             RiskReportView riskReport,
                             ReserveStateView reserveState,
                             AllocationView allocation) {
        this.snapshot = snapshot;
        this.opportunities = opportunities == null
                ? List.of()
                : Collections.unmodifiableList(opportunities);
        this.riskReport = riskReport;
        this.reserveState = reserveState;
        this.allocation = allocation;
    }

    /**
     * Tagastab turu/hinna hetkeseisu.
     */
    public TPriceSnapshot getSnapshot() {
        return snapshot;
    }

    /**
     * Tagastab tuvastatud võimaluste loendi.
     */
    public List<TOpportunity> getOpportunities() {
        return opportunities;
    }

    /**
     * Tagastab riskiraporti vaate.
     */
    public RiskReportView getRiskReport() {
        return riskReport;
    }

    /**
     * Tagastab reserviseisundi vaate.
     */
    public ReserveStateView getReserveState() {
        return reserveState;
    }

    /**
     * Tagastab jaotuse vaate.
     */
    public AllocationView getAllocation() {
        return allocation;
    }
}