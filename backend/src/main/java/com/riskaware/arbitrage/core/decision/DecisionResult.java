package com.riskaware.arbitrage.core.decision;

import java.util.LinkedHashMap;
import java.util.Map;

public final class DecisionResult {

    private final boolean allowed;
    private final DecisionReason reason;
    private final String message;
    private final Map<String, Object> details;

    private DecisionResult(boolean allowed, DecisionReason reason, String message, Map<String, Object> details) {
        this.allowed = allowed;
        this.reason = reason;
        this.message = message;
        this.details = details;
    }

    public boolean isAllowed() { return allowed; }

    // Backward-compat with pipeline naming
    public boolean isGo() { return allowed; }

    public DecisionReason reason() { return reason; }
    public String message() { return message; }
    public Map<String, Object> details() { return details; }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private boolean allowed;
        private DecisionReason reason = DecisionReason.POLICY_BLOCKED;
        private String message;
        private Map<String, Object> details;

        // --- Compatibility with DecisionEngine API ---
        public Builder go(boolean go) {
            this.allowed = go;
            return this;
        }

        public Builder addReason(DecisionReason r) {
            this.reason = r;
            putDetailReason(r);
            return this;
        }        

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
        public Builder explain(String explain) {
            // DecisionEngine kasutab "explain" välja; map'ime selle message alla.
            this.message = explain;
            return this;
        }

        public Builder riskScore(Double riskScore) {
            putDetail("riskScore", riskScore);
            return this;
        }

        public Builder reserveLimitExceeded(Boolean reserveLimitExceeded) {
            putDetail("reserveLimitExceeded", reserveLimitExceeded);
            return this;
        }

        public Builder allocationFeasible(Boolean allocationFeasible) {
            putDetail("allocationFeasible", allocationFeasible);
            return this;
        }

        private void putDetail(String key, Object value) {
            if (this.details == null) this.details = new LinkedHashMap<>();
            this.details.put(key, value);
        }        

        public Builder allowed(boolean allowed) {
            this.allowed = allowed;
            return this;
        }

        public Builder reason(DecisionReason reason) {
            this.reason = reason;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder details(Map<String, Object> details) {
            this.details = details;
            return this;
        }

        public DecisionResult build() {
            return new DecisionResult(allowed, reason, message, details);
        }
    }
}