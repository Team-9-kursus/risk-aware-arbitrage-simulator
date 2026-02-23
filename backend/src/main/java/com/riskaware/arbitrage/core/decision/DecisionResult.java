package com.riskaware.arbitrage.core.decision;

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