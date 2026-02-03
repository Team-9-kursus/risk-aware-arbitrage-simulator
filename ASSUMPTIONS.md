# ASSUMPTIONS
## System Assumptions - risk aware arbitrage simulator

**Status:** Locked  
**Purpose:** Define the assumptions under which the system is designed, evaluated, and graded

---

## 1. Simulation Context

The system operates under the assumption that:

- all actions occur in a **simulated environment**
- no interaction with real financial markets exists
- no real capital is at risk
- results are illustrative, not predictive

---

## 2. Price Data Assumptions

The system assumes that:

- price data may be delayed
- price data may be incomplete
- price data may be mocked
- price data does not represent real-time market conditions

Accuracy is **not guaranteed** and is **not required**.

---

## 3. Risk Model Simplification

The risk model assumes:

- simplified representations of fees, latency, liquidity, and volatility
- heuristic-based scoring
- no claim of financial realism or institutional accuracy

The purpose is **risk-aware decision flow**, not precise risk estimation.

---

## 4. Deterministic Behavior

The system assumes that:

- given the same inputs, outputs are repeatable
- simulations are deterministic
- randomness is avoided unless explicitly documented

This supports debugging, testing, and evaluation.

---

## 5. Reserve & Allocation Logic

It is assumed that:

- reserves are virtual and simulated
- exposure limits are predefined
- allocation strategies are simplified
- reserve depletion has no real-world consequence

Reserve logic exists to demonstrate **system constraints**, not portfolio theory.

---

## 6. No Adversarial Environment

The system assumes:

- no malicious actors
- no adversarial trading behavior
- no market manipulation
- no security threats

Security hardening is out of scope.

---

## 7. Single-System Perspective

The system assumes:

- a single logical decision-making system
- no competing agents
- no market-wide feedback loops
- no emergent multi-agent dynamics

---

## 8. Infrastructure Assumptions

The system assumes:

- local or controlled runtime environment
- limited load
- no high-availability requirements
- no horizontal scaling

Infrastructure exists only to support development and demonstration.

---

## 9. Configuration Stability

The system assumes:

- configuration values are valid
- thresholds are reasonable
- misconfiguration is not a primary concern

Error handling focuses on logic clarity, not resilience.

---

## 10. Educational Evaluation Context

The system assumes:

- evaluation is performed by instructors
- correctness is judged by architecture and reasoning
- clarity is prioritized over completeness
- documented intent matters more than output metrics

---

## Final Note

> Assumptions are not weaknesses.  
> Undocumented assumptions are.

All behavior in risk aware arbitrage simulator must be interpreted within the boundaries defined here.
