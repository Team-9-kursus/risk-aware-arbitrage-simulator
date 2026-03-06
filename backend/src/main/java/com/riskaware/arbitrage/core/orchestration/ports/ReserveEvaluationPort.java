package com.riskaware.arbitrage.core.orchestration.ports;

/**
 * Evaluates reserve constraints for a given opportunity.
 */
public interface ReserveEvaluationPort<TOpportunity> {

    Object evaluate(TOpportunity opportunity, Object riskReport);

}