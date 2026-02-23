package com.riskaware.arbitrage.core.decision;

import com.riskaware.arbitrage.core.orchestration.contracts.AllocationView;
import com.riskaware.arbitrage.core.orchestration.contracts.RiskReportView;
import com.riskaware.arbitrage.core.orchestration.contracts.ReserveStateView;

import java.util.EnumSet;
import java.util.Set;

/**
 * Otsustusmootor, mis teeb GO/NO-GO otsuse enne simulatsiooni käivitamist.
 * Käitumine peab olema deterministlik (ei sõltu ajast ega juhuslikkusest).
 */
public final class DecisionEngine {

    /**
     * Otsustuspoliitika, mis määrab läved ja blokeerimisreeglid.
     */
    private final DecisionPolicy policy;

    /**
     * Konstruktor.
     * Kui poliitika on null, kasutatakse vaikimisi väärtusi.
     */
    public DecisionEngine(DecisionPolicy policy) {
        this.policy = policy == null ? DecisionPolicy.defaults() : policy;
    }

    /**
     * Tagastab kasutatava otsustuspoliitika.
     */
    public DecisionPolicy getPolicy() {
        return policy;
    }

    /**
     * Teeb GO/NO-GO otsuse järgmiste sisendite põhjal:
     *
     * @param hasOpportunities  kas leidub arbitraaživõimalusi
     * @param riskReport        riskiraporti vaade
     * @param reserveState      reserviseisundi vaade
     * @param allocation        jaotuse (allocation) vaade
     * @return DecisionResult   struktureeritud otsuse tulemus koos põhjustega
     */
    public DecisionResult decide(boolean hasOpportunities,
                                 RiskReportView riskReport,
                                 ReserveStateView reserveState,
                                 AllocationView allocation) {

        // Kogume kõik NO-GO põhjused siia hulka
        Set<DecisionReason> reasons = EnumSet.noneOf(DecisionReason.class);

        // 1. Kontroll: kas üldse on võimalusi
        if (!hasOpportunities) {
            reasons.add(DecisionReason.NO_OPPORTUNITIES);
        }

        // 2. Riskikontroll
        Double riskScore = null;
        if (riskReport == null) {
            // Riskiraport puudub
            reasons.add(DecisionReason.RISK_REPORT_MISSING);
        } else {
            riskScore = riskReport.getRiskScore();
            // Kui risk ületab poliitikas määratud maksimaalse läve
            if (riskScore != null && riskScore > policy.getMaxRiskScore()) {
                reasons.add(DecisionReason.RISK_TOO_HIGH);
            }
        }

        // 3. Reservikontroll
        Boolean reserveLimitExceeded = null;
        if (reserveState == null) {
            // Reserviseisundi info puudub
            reasons.add(DecisionReason.RESERVE_STATE_MISSING);
        } else {
            reserveLimitExceeded = reserveState.isLimitExceeded();
            // Kui poliitika nõuab blokeerimist ja limiit on ületatud
            if (policy.isBlockOnReserveLimitExceeded() && Boolean.TRUE.equals(reserveLimitExceeded)) {
                reasons.add(DecisionReason.RESERVE_LIMIT_EXCEEDED);
            }
        }

        // 4. Jaotuse (allocation) kontroll
        Boolean allocationFeasible = null;
        if (allocation == null) {
            // Jaotuse info puudub
            reasons.add(DecisionReason.ALLOCATION_MISSING);
        } else {
            allocationFeasible = allocation.isFeasible();
            // Kui poliitika nõuab blokeerimist ja jaotus ei ole teostatav
            if (policy.isBlockOnAllocationNotFeasible() && Boolean.FALSE.equals(allocationFeasible)) {
                reasons.add(DecisionReason.ALLOCATION_NOT_FEASIBLE);
            }
        }

        // GO, kui ühtegi blokeerivat põhjust ei lisatud
        boolean go = reasons.isEmpty();

        // Inimloetav selgitus
        String explain = go
                ? "GO: poliitika kontrollid läbitud."
                : "NO-GO: " + reasons;

        // Koosta otsuse tulemus builderi abil
        DecisionResult.Builder b = DecisionResult.builder()
                .go(go)
                .explain(explain)
                .riskScore(riskScore)
                .reserveLimitExceeded(reserveLimitExceeded)
                .allocationFeasible(allocationFeasible);

        // Lisa kõik tuvastatud põhjused tulemustesse
        for (DecisionReason r : reasons) {
            b.addReason(r);
        }

        return b.build();
    }
}