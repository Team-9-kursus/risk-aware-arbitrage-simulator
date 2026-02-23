package com.riskaware.arbitrage.core.decision;

/**
 * Otsuse põhjused, miks simulatsioon lubatakse või blokeeritakse.
 *
 * NB!
 * - Enum väärtusi ei tohi ümber nimetada.
 * - Väärtusi kasutatakse logides, dokumentatsioonis ja UI selgitustes.
 * - Uusi põhjuseid võib lisada, olemasolevaid ei muudeta.
 */
public enum DecisionReason {

    // Risk
    RISK_TOO_HIGH,
    RISK_REPORT_MISSING,

    // Reservipiirangud
    RESERVE_LIMIT_EXCEEDED,
    RESERVE_STATE_MISSING,

    // Jaotuse teostatavus
    ALLOCATION_NOT_FEASIBLE,
    ALLOCATION_MISSING,

    // Arbitraaživõimaluse kehtivus
    NO_OPPORTUNITIES,

    // Poliitikapõhine / muu
    POLICY_BLOCKED,
    ALLOWED
}