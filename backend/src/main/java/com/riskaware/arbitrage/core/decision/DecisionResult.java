package com.riskaware.arbitrage.core.decision;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Immutable decision result.
 */
public final class DecisionResult {

    /** True if the action is allowed. */
    private final boolean allowed;

    /** Primary decision reason. */
    private final DecisionReason reason;

    /** Human-readable explanation. */
    private final String message;

    /** Optional decision details. */
    private final Map<String, Object> details;

    private DecisionResult(boolean allowed, DecisionReason reason, String message, Map<String, Object> details) {
        this.allowed = allowed;
        this.reason = reason;
        this.message = message;
        this.details = details;
    }

    /** Returns whether the action is allowed. */
    public boolean isAllowed() {
        return allowed;
    }

    /** Compatibility alias used by the pipeline. */
    public boolean isGo() {
        return allowed;
    }

    /** Returns the primary decision reason. */
    public DecisionReason reason() {
        return reason;
    }

    /** Returns the explanation message. */
    public String message() {
        return message;
    }

    /** Returns optional decision details. */
    public Map<String, Object> details() {
        return details;
    }

    /** Creates a builder instance. */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for DecisionResult.
     */
    public static final class Builder {

        private boolean allowed;
        private DecisionReason reason = DecisionReason.POLICY_BLOCKED;
        private String message;
        private Map<String, Object> details;

        /** Compatibility alias for allowed. */
        public Builder go(boolean go) {
            this.allowed = go;
            return this;
        }

        /** Adds a decision reason and stores it in details. */
        public Builder addReason(DecisionReason r) {
            this.reason = r;
            putDetailReason(r);
            return this;
        }

        /** Adds a reason entry under "reasons". */
        @SuppressWarnings("unchecked")
        private void putDetailReason(DecisionReason r) {
            if (this.details == null) {
                this.details = new LinkedHashMap<>();
            }

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

        /** Compatibility alias for message. */
        public Builder explain(String explain) {
            this.message = explain;
            return this;
        }

        /** Adds risk score detail. */
        public Builder riskScore(Double riskScore) {
            putDetail("riskScore", riskScore);
            return this;
        }

        /** Adds reserve limit detail. */
        public Builder reserveLimitExceeded(Boolean reserveLimitExceeded) {
            putDetail("reserveLimitExceeded", reserveLimitExceeded);
            return this;
        }

        /** Adds allocation feasibility detail. */
        public Builder allocationFeasible(Boolean allocationFeasible) {
            putDetail("allocationFeasible", allocationFeasible);
            return this;
        }

        /** Adds a generic detail entry. */
        private void putDetail(String key, Object value) {
            if (this.details == null) {
                this.details = new LinkedHashMap<>();
            }
            this.details.put(key, value);
        }

        /** Sets allowed state. */
        public Builder allowed(boolean allowed) {
            this.allowed = allowed;
            return this;
        }

        /** Sets the primary reason. */
        public Builder reason(DecisionReason reason) {
            this.reason = reason;
            return this;
        }

        /** Sets the message. */
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        /** Replaces the full details map. */
        public Builder details(Map<String, Object> details) {
            this.details = details;
            return this;
        }

        /** Builds the immutable result. */
        public DecisionResult build() {
            return new DecisionResult(allowed, reason, message, details);
        }
    }
}