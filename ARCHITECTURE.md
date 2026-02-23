# ARCHITECTURE
## System Architecture - risk aware arbitrage simulator

**Status:** Locked  
**Purpose:** Define system structure, component responsibilities, and data flow

---

## 1. Architectural Principles

Risk aware arbitrage simulator is designed according to the following principles:

- **Risk-first decision making** - decisions precede simulation
- **Explicit responsibility boundaries** - no hidden logic
- **Simulation-only execution** - no real-world side effects
- **Explainability over optimization**
- **Deterministic behavior**

The architecture favors **clarity and traceability** over performance.

---

## 2. High-Level System Overview

The system is composed of a **single backend service** and a **separate frontend**, connected via a DTO-based REST API.

```

Frontend (React)
↓
REST API (DTOs)
↓
Decision & Simulation Backend (Spring Boot)

```

---

## 3. Backend Architectural Layers

The backend follows a **layered and modular structure**, not a strict hexagonal or onion architecture, to keep educational clarity.

### 3.1 API Layer (`api`)

**Responsibility:**
- Expose REST endpoints
- Validate input
- Map requests/responses to DTOs
- Never contain business logic

**Key Components:**
- Controllers
- DTO definitions

**Rules:**
- No domain logic
- No state mutation
- No cross-module shortcuts

---

### 3.2 Core Domain Layer (`core`)

The core contains **all decision-making logic**.

It is subdivided by responsibility, not by technical concern.

---

#### 3.2.1 Data Ingestion (`core.ingestion`)

**Responsibility:**
- Provide price snapshots
- Abstract data source origin

**Characteristics:**
- Mocked or limited API sources
- No real-time guarantees

---

#### 3.2.2 Opportunity Detection (`core.opportunity`)

**Responsibility:**
- Identify potential arbitrage opportunities
- Compare prices across sources

**Output:**
- Zero or more `ArbitrageOpportunity` objects

---

#### 3.2.3 Risk Evaluation (`core.risk`)

**Responsibility:**
- Evaluate risk before any simulation
- Aggregate multiple risk factors

**Risk Factors:**
- Fee risk
- Latency risk
- Liquidity risk
- Volatility risk

Each factor is **independent and composable**.

---

#### 3.2.4 Reserve Management (`core.reserve`)

**Responsibility:**
- Track simulated reserves
- Enforce exposure limits
- Prevent over-allocation

Reserves act as **system-level constraints**, not user balances.

---

#### 3.2.5 Allocation Strategies (`core.allocation`)

**Responsibility:**
- Determine how much capital would be allocated
- Apply heuristics based on risk and reserves

Allocation never overrides reserve limits.

---

#### 3.2.6 Decision Engine (`core.decision`)

**Responsibility:**
- Produce an explicit decision outcome
- Return a structured GO / NO-GO result

**Output:**
- Decision result
- Decision reasons (explainable)

This is the **single authority** for execution permission.

---

#### 3.2.7 Orchestration (`core.orchestration`)

**Responsibility:**
- Coordinate the evaluation pipeline
- Enforce execution order

Pipeline order is fixed and intentional.

---

### 3.3 Simulation Layer (`simulation`)

**Responsibility:**
- Simulate trade execution
- Calculate PnL
- Produce deterministic results

**Key Rule:**
Simulation is **never executed** without an explicit GO decision.

---

### 3.4 Analytics Layer (`analytics`)

**Responsibility:**
- Analyze outcomes
- Compare risk vs result
- Produce metrics for visualization

Analytics do not affect decisions.

---

## 4. End-to-End Decision Flow

```

Price Ingestion
↓
Opportunity Detection
↓
Risk Evaluation
↓
Reserve Check
↓
Allocation Strategy
↓
Decision Engine (GO / NO-GO)
↓
[GO] → Simulation → Analytics
[NO-GO] → Explainable rejection

```

---

## 5. Frontend Architecture

The frontend is a **consumer**, not a decision-maker.

### Responsibilities:
- Display system state
- Visualize risk levels
- Show decision outcomes
- Present simulation history

### Non-Responsibilities:
- No risk calculation
- No business logic
- No decision-making

---

## 6. DTO-Based Communication

All frontend-backend communication is performed using DTOs.

**Benefits:**
- Stable contracts
- No domain leakage
- Backend refactoring safety
- Frontend simplicity

---

## 7. Configuration & Extensibility

The architecture allows future extension of:
- risk factors
- data sources
- allocation strategies
- analytics modules

Without breaking existing contracts.

---

## 8. Explicit Architectural Constraints

- No hidden execution paths
- No implicit state changes
- No cross-layer shortcuts
- No simulation without decision
- No frontend-driven logic

---

## Final Note

> Architecture is the discipline of making correct behavior inevitable.

Risk aware arbitrage simulator architecture enforces responsibility, explainability, and restraint by design.

---
