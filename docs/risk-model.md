# Risk Model
## Risk Evaluation Framework - risk aware arbitrage simulator

**Status:** Locked  
**Purpose:** Describe how risk is modeled, evaluated, and used in decision-making

---

## 1. Risk Model Philosophy

Risk aware arbitrage simulator applies a **risk-first evaluation model**.

The system does not ask:
> “How profitable is this opportunity?”

Instead, it asks:
> “Is this opportunity acceptable under defined risk constraints?”

Risk evaluation exists to **block unsafe behavior**, not to optimize outcomes.

---

## 2. Risk Evaluation Timing

All risk evaluation is performed:

- **before** simulation
- **before** any allocation
- **before** any execution logic

If risk exceeds predefined thresholds, the system produces a **NO-GO decision** and halts further processing.

---

## 3. Risk Score Structure

Each arbitrage opportunity produces a `RiskReport` consisting of:

- individual risk factor scores
- an aggregated risk score
- explanatory metadata

Risk scores are **normalized** to a common scale for comparability.

---

## 4. Risk Factors

Each risk factor is evaluated independently.  
No factor overrides another implicitly.

---

### 4.1 Fee Risk

**Description:**  
Represents the impact of transaction fees relative to the expected price spread.

**Considerations:**
- entry fee
- exit fee
- total fee burden

**Risk Increases When:**
- fees approach or exceed spread
- fee variance is unknown

**Purpose:**  
Prevent simulations where fees nullify or dominate potential outcomes.

---

### 4.2 Latency Risk

**Description:**  
Represents execution delay risk between price observation and simulated execution.

**Considerations:**
- data freshness
- assumed network delay
- exchange response time

**Risk Increases When:**
- latency assumptions are high
- price volatility is non-negligible

**Purpose:**  
Model slippage and stale data effects.

---

### 4.3 Liquidity Risk

**Description:**  
Represents the risk that the required volume cannot be executed at observed prices.

**Considerations:**
- simulated order book depth
- required trade size
- volume concentration

**Risk Increases When:**
- trade size approaches available liquidity
- liquidity data is incomplete or mocked

**Purpose:**  
Prevent unrealistic execution assumptions.

---

### 4.4 Volatility Risk

**Description:**  
Represents the likelihood of adverse price movement during execution.

**Considerations:**
- recent price variance
- assumed market instability
- volatility heuristics

**Risk Increases When:**
- volatility is high
- latency is non-zero

**Purpose:**  
Model adverse price movement risk.

---

## 5. Risk Aggregation

Individual risk factors are combined into an aggregated risk score.

### Aggregation Rules:
- no single factor is ignored
- aggregation logic is explicit
- weights (if used) are documented
- thresholds are system-defined

The aggregation produces a **qualitative risk level**, not a prediction.

---

## 6. Risk Thresholds

Risk thresholds are:

- predefined
- conservative
- system-wide
- configuration-driven

Thresholds determine whether an opportunity proceeds to allocation and simulation.

---

## 7. Explainability

Every risk evaluation produces:

- individual factor scores
- aggregated risk outcome
- reasons for rejection (if applicable)

Explainability is a **first-class requirement**.

---

## 8. Risk vs Outcome Separation

Risk evaluation:

- does not depend on simulation results
- does not adapt based on past success
- does not self-optimize

Risk and outcome are intentionally decoupled.

---

## 9. Non-Goals of the Risk Model

The risk model does **not**:

- predict profitability
- model real-world market dynamics
- adapt using machine learning
- claim financial accuracy

---

## 10. Educational Intent

The risk model exists to demonstrate:

- structured risk decomposition
- pre-execution decision gates
- responsible system behavior
- architectural clarity

---

## Final Note

> Risk models do not exist to predict the future.  
> They exist to prevent avoidable mistakes.

Risk aware arbitrage simulator risk model enforces restraint before execution.