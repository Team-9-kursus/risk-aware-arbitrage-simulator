package com.riskaware.arbitrage.core.decision;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * DecisionResult esindab otsuse lõpptulemust
 * (kas simulatsioon on lubatud või blokeeritud)
 * koos põhjuse ja selgitavate detailidega.
 *
 * Immutable objekt – loodav ainult Builderi kaudu.
 */
public final class DecisionResult {

    /** Kas tegevus on lubatud (true) või blokeeritud (false). */
    private final boolean allowed;

    /** Peamine otsuse põhjus (enum). */
    private final DecisionReason reason;

    /** Inimloetav selgitus (explainability). */
    private final String message;

    /**
     * Lisadetailid otsuse kohta (riskiskoor, reserve info jne).
     * Hoitakse Map kujul, et võimaldada paindlikku laiendamist.
     */
    private final Map<String, Object> details;

    private DecisionResult(boolean allowed, DecisionReason reason, String message, Map<String, Object> details) {
        this.allowed = allowed;
        this.reason = reason;
        this.message = message;
        this.details = details;
    }

    /** Kas otsus lubab edasi minna. */
    public boolean isAllowed() { return allowed; }

    /**
     * Backward-compat pipeline'i jaoks.
     * Pipeline kasutab nime isGo().
     */
    public boolean isGo() { return allowed; }

    /** Tagastab peamise otsuse põhjuse. */
    public DecisionReason reason() { return reason; }

    /** Tagastab inimloetava selgituse. */
    public String message() { return message; }

    /** Tagastab lisadetailid otsuse kohta. */
    public Map<String, Object> details() { return details; }

    /** Builder pattern kasutamiseks. */
    public static Builder builder() { return new Builder(); }

    /**
     * Builder võimaldab samm-sammult
     * otsuse konstruktsiooni koos explainability detailidega.
     */
    public static final class Builder {

        private boolean allowed;
        private DecisionReason reason = DecisionReason.POLICY_BLOCKED;
        private String message;
        private Map<String, Object> details;

        // --- Compatibility with DecisionEngine API ---

        /**
         * Alternatiivne nimetus allowed jaoks (DecisionEngine kasutab go).
         */
        public Builder go(boolean go) {
            this.allowed = go;
            return this;
        }

        /**
         * Lisab otsuse põhjuse ning salvestab selle ka detailide alla.
         */
        public Builder addReason(DecisionReason r) {
            this.reason = r;
            putDetailReason(r);
            return this;
        }

        /**
         * Lisab põhjuse detailide kaardile massiivina ("reasons").
         * Toetab mitut põhjust.
         */
        @SuppressWarnings("unchecked")
        private void putDetailReason(DecisionReason r) {
            if (this.details == null) this.details = new LinkedHashMap<>();
            var key = "reasons";
            var existing = this.details.get(key);

            if (existing == null) {
                this.details.put(key, new java.util.ArrayList<>(java.util.List.of(r.name())));
                return;
            }

            if (existing instanceof java.util.List<?> list) {
                ((java.util.List<Object>) list).add(r.name());
            }
        }

        // --- Compatibility with DecisionEngine explainability/details ---

        /**
         * DecisionEngine kasutab explain välja;
         * mapime selle message väljale.
         */
        public Builder explain(String explain) {
            this.message = explain;
            return this;
        }

        /** Lisab riskiskoori detailide hulka. */
        public Builder riskScore(Double riskScore) {
            putDetail("riskScore", riskScore);
            return this;
        }

        /** Märgib, kas reserve limiit on ületatud. */
        public Builder reserveLimitExceeded(Boolean reserveLimitExceeded) {
            putDetail("reserveLimitExceeded", reserveLimitExceeded);
            return this;
        }

        /** Märgib, kas allocation oli teostatav. */
        public Builder allocationFeasible(Boolean allocationFeasible) {
            putDetail("allocationFeasible", allocationFeasible);
            return this;
        }

        /**
         * Üldine detailide lisamise abimeetod.
         */
        private void putDetail(String key, Object value) {
            if (this.details == null) this.details = new LinkedHashMap<>();
            this.details.put(key, value);
        }

        /** Seadistab allowed välja. */
        public Builder allowed(boolean allowed) {
            this.allowed = allowed;
            return this;
        }

        /** Seadistab peamise otsuse põhjuse. */
        public Builder reason(DecisionReason reason) {
            this.reason = reason;
            return this;
        }

        /** Seadistab selgitava sõnumi. */
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        /** Asendab detailide kaardi täielikult. */
        public Builder details(Map<String, Object> details) {
            this.details = details;
            return this;
        }

        /** Lõplik objekti konstrueerimine. */
        public DecisionResult build() {
            return new DecisionResult(allowed, reason, message, details);
        }
    }
}