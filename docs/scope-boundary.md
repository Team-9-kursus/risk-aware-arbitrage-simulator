# Scope Boundary
## Explicit Project Scope - risk aware arbitrage simulator

**Status:** Locked  
**Purpose:** Define what is inside and outside the project scope

---

## 1. Purpose of Scope Definition

This document exists to:

- prevent scope creep
- align team expectations
- support fair academic evaluation
- protect architectural intent

Anything not explicitly included is considered **out of scope**.

---

## 2. In-Scope Functionality

The project includes the following functional scope.

### 2.1 Market Scope
- Spot-market arbitrage only
- Two-market comparison (Exchange A vs Exchange B)
- Single-asset evaluation per opportunity

---

### 2.2 Data Scope
- Mocked price data
- Limited or delayed API data
- Non-real-time price snapshots

Accuracy and freshness are **not guaranteed**.

---

### 2.3 Risk Scope
- Fee risk
- Latency risk
- Liquidity risk
- Volatility risk

Risk evaluation is:
- heuristic-based
- pre-execution
- deterministic

---

### 2.4 Decision Scope
- Explicit GO / NO-GO decision
- Reserve-based thresholds
- Allocation heuristics
- Explainable rejection reasons

---

### 2.5 Simulation Scope
- Deterministic trade simulation
- PnL calculation
- No real execution
- No side effects outside the system

---

### 2.6 Visualization Scope
- Risk level display
- Decision outcomes
- Simulation history
- Analytics summaries

---

## 3. Out-of-Scope Functionality

The following areas are explicitly excluded.

---

### 3.1 Trading & Finance
- Live trading
- Real money usage
- User portfolios
- Profit optimization
- Financial advice

---

### 3.2 Market Complexity
- Triangular arbitrage
- Multi-leg strategies
- Derivatives, futures, options
- Leveraged or margin trading
- MEV or frontrunning logic

---

### 3.3 System Complexity
- Microservices
- Distributed systems
- Message queues
- Event-driven architectures
- Horizontal scaling

---

### 3.4 Performance & Reliability
- Low-latency guarantees
- High-throughput execution
- Fault tolerance
- High availability
- Disaster recovery

---

### 3.5 Security & Compliance
- Authentication & authorization
- Financial regulation compliance
- Penetration testing
- Production security hardening

---

## 4. Boundary Enforcement

Scope boundaries are enforced by:

- architecture design
- documented non-goals
- simulation-only execution
- explicit decision gates

Any feature violating these boundaries is rejected by design.

---

## 5. Change Policy

Changes to scope require:

- explicit documentation update
- team agreement
- architectural justification

Undocumented scope expansion is not permitted.

---

## 6. Academic vs External Scope

All work in this repository is limited to academic exploration.

Any continuation beyond this boundary must occur outside this repository and is not governed by the constraints, requirements, or evaluation criteria of this project.

---

## Final Note

> A project without boundaries becomes a collection of unfinished ideas.

Risk aware arbitrage simulator scope is intentionally constrained to support clarity, responsibility, and learning.