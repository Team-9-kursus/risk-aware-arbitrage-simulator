# DECISIONS
## Architectural & Design Decisions - Risk aware arbitrage simulator

**Status:** Locked  
**Purpose:** Documenting intentional system decisions and their rationale

---

## 1. Simulation-Only Architecture

### Decision
The system operates **strictly at simulation level**.

### Rationale
- avoids legal and ethical risks
- allows deterministic testing
- enables focus on architecture and decision logic
- removes dependency on external trading systems

---

## 2. Risk Evaluation Before Simulation

### Decision
All risk evaluation occurs **before** any trade simulation is executed.

### Rationale
- reflects responsible real-world system behavior
- prevents “simulate first, justify later” logic
- enforces decision gates
- simplifies reasoning and debugging

---

## 3. Explicit GO / NO-GO Decision Gate

### Decision
A dedicated **Decision Engine** produces an explicit GO / NO-GO result.

### Rationale
- makes decisions observable and testable
- avoids implicit behavior
- improves transparency
- supports explainability

---

## 4. Reserve-Based Thresholds

### Decision
Simulation is blocked when reserve exposure exceeds predefined limits.

### Rationale
- models capital preservation concepts
- prevents over-allocation
- introduces system-level constraints
- demonstrates risk-aware allocation

---

## 5. Modular Risk Factors

### Decision
Risk is evaluated using **independent risk factors** (fees, latency, liquidity, volatility).

### Rationale
- supports extensibility
- allows isolated testing
- avoids monolithic scoring logic
- reflects real-world risk decomposition

---

## 6. Deterministic Simulation Logic

### Decision
Trade simulations are deterministic.

### Rationale
- repeatable outcomes
- simpler debugging
- easier validation
- suitable for educational context

---

## 7. Separation of Concerns

### Decision
The system is divided into clearly scoped modules:
- ingestion
- opportunity
- risk
- reserve
- decision
- simulation
- analytics

### Rationale
- improves maintainability
- enforces responsibility boundaries
- supports team collaboration
- reflects clean architecture principles

---

## 8. DTO-Based API Contract

### Decision
Frontend-backend communication is done strictly via DTOs.

### Rationale
- prevents domain leakage
- stabilizes API contracts
- simplifies frontend development
- allows backend refactoring without UI breakage

---

## 9. No Hidden Side Effects

### Decision
All state changes are explicit and observable.

### Rationale
- improves predictability
- simplifies testing
- avoids implicit mutations
- supports auditability

---

## 10. Educational Over Optimization

### Decision
Design clarity is prioritized over performance or optimization.

### Rationale
- aligns with project goals
- avoids premature optimization
- improves explainability
- supports teaching and evaluation

---

## 11. Configuration Over Hardcoding

### Decision
Thresholds and limits are configurable where meaningful.

### Rationale
- supports experimentation
- avoids magic numbers
- improves adaptability
- keeps logic transparent

---

## 12. Explicit Non-Goals

### Decision
All non-goals are explicitly documented and enforced.

### Rationale
- prevents scope creep
- protects architectural integrity
- clarifies expectations
- supports ethical boundaries

---

## Final Note

> A system without documented decisions is indistinguishable  
> from a system built by accident.

Risk aware arbitrage simulator behavior is intentional, constrained, and explainable.
