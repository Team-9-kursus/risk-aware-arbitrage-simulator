# Architecture Justification
## Why risk aware arbitrage simulator Is Designed This Way

**Status:** Locked  
**Purpose:** Explain and justify architectural choices

---

## 1. Architectural Intent

Risk aware arbitrage simulator is not designed to be a trading system.  
It is designed to be a **decision-making system under uncertainty**.

The architecture exists to make **incorrect behavior difficult** and **correct behavior inevitable**.

---

## 2. Simulation-Only Design

### Justification
Running the system strictly at simulation level:

- eliminates legal and ethical risks
- avoids dependency on external exchanges
- ensures deterministic behavior
- enables full observability

This allows the system to focus on **architecture and decision flow**, not operational complexity.

---

## 3. Risk-First Decision Flow

### Justification
Most simplified arbitrage systems:

> detect opportunity → simulate → explain result

Risk aware arbitrage simulator inverts this:

> detect opportunity → **evaluate risk** → decide → simulate (if allowed)

This enforces **responsible system behavior** and prevents outcome-driven justification.

---

## 4. Explicit Decision Gate

### Justification
The GO / NO-GO decision is:

- explicit
- observable
- explainable
- testable

This avoids implicit execution and hidden control flow.

---

## 5. Reserve-Based Constraints

### Justification
Introducing reserve constraints:

- models capital preservation concepts
- enforces system-wide limits
- prevents over-allocation
- introduces realistic constraints without real money

Reserves act as **architectural guardrails**, not financial instruments.

---

## 6. Modular Risk Factors

### Justification
Separating risk into independent factors:

- avoids monolithic scoring logic
- supports extension
- allows isolated reasoning
- improves explainability

Each factor has a **single responsibility**.

---

## 7. Deterministic Simulation

### Justification
Deterministic simulation:

- ensures reproducibility
- simplifies debugging
- supports evaluation
- avoids statistical noise

This aligns with educational objectives.

---

## 8. Separation of Concerns

### Justification
Clear module boundaries:

- prevent logic leakage
- improve maintainability
- enable parallel team work
- reflect clean architecture principles

No layer is allowed to “cheat”.

---

## 9. DTO-Based API Contracts

### Justification
Using DTOs for all communication:

- stabilizes frontend-backend interaction
- prevents domain model exposure
- allows backend refactoring
- simplifies frontend development

This reinforces architectural discipline.

---

## 10. Avoidance of Over-Engineering

### Justification
The architecture avoids:

- microservices
- message brokers
- distributed systems
- premature optimization

This keeps the system **understandable, testable, and justifiable**.

---

## 11. Educational Evaluation Alignment

### Justification
The architecture is aligned with academic evaluation by:

- making decisions explicit
- documenting intent
- enforcing constraints
- prioritizing reasoning over output

---

## Final Note

> Good architecture is not about adding complexity.  
> It is about removing the ability to do the wrong thing.

Risk aware arbitrage simulator architecture is designed to enforce clarity, responsibility, and restraint.