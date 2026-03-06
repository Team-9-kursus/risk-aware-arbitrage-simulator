package com.riskaware.arbitrage.core.decision;

/**
 * Reasons explaining why a decision is GO or NO-GO.
 *
 * Do not rename existing values.
 * They may be used in logs, APIs and UI explanations.
 */
public enum DecisionReason {

    // Risk
    RISK_TOO_HIGH,
    RISK_REPORT_MISSING,

    // Reserve constraints
    RESERVE_LIMIT_EXCEEDED,
    RESERVE_STATE_MISSING,

    // Allocation feasibility
    ALLOCATION_NOT_FEASIBLE,
    ALLOCATION_MISSING,

    // Opportunity validation
    NO_OPPORTUNITIES,

    // Policy / fallback
    POLICY_BLOCKED,
    ALLOWED
}