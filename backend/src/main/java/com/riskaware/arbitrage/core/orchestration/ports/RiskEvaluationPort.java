package com.riskaware.arbitrage.core.orchestration.ports;

/**
 * Evaluates risk for a given opportunity.
 */
public interface RiskEvaluationPort<TOpportunity> {

    Object evaluate(TOpportunity opportunity);

}