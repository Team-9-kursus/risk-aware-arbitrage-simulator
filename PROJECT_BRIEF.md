# Project Brief
## Risk-Aware Arbitrage Simulation Platform - Risk Aware Arbitrage Simulator

**Version:** 1.1  
**Status:** Locked  
**Project Type:** Educational / System Architecture & Decision Logic  
**Language:** English (technical documentation)

---

## 1. Project Concept & Motivation

The purpose of this team project is to design and implement a **risk-aware arbitrage simulation platform** that prioritizes **correct system design and decision logic** over profit calculation or real trading.

Arbitrage refers to a situation where the same asset is temporarily priced differently across multiple markets, theoretically allowing a buy-low / sell-high strategy.  
In practice, however, arbitrage outcomes are heavily affected by risk factors such as:

- transaction fees
- liquidity constraints
- execution latency
- price volatility
- technical failures

Many simplified solutions focus only on price differences and ignore these risks.  
**Risk Aware Arbitrage Simulator explicitly addresses this problem.**

---

## 2. Problem Statement

How can we design a system that:

- does not evaluate arbitrage opportunities solely based on potential profit,
- evaluates **risk before execution**,
- decides **not to simulate** a trade when risk exceeds predefined limits,
- incorporates reserve-based thresholds into decision-making,
- demonstrates responsible system behavior under uncertainty?

---

## 3. Proposed Solution

Risk Aware Arbitrage Simulator is a **simulation-based platform** that:

1. Collects price data from multiple sources (mocked or limited APIs)
2. Detects potential arbitrage opportunities
3. Evaluates risks **before** any simulation
4. Applies reserve-based thresholds and allocation heuristics
5. Simulates a trade **only if risk is acceptable**

All actions are performed strictly at **simulation level**:

- No real money
- No real trades
- No financial advice

---

## 4. Project Goals

The project aims to demonstrate:

- multi-component system architecture
- clear responsibility separation
- risk-based decision logic
- reserve-aware system behavior
- structured and documented design decisions

The focus is **system thinking**, not individual algorithms.

---

## 5. Non-Goals (Explicit Limitations)

The project does **not** include:

- live trading
- real money usage
- profit maximization
- production certification
- financial recommendations

---

## 5.1 Scope Boundary

### Included
- Spot-market arbitrage (Exchange A vs Exchange B)
- Simple order types at simulation level
- Deterministic simulation logic

### Excluded
- Triangular arbitrage
- Derivatives, futures, options
- High-frequency trading
- MEV or frontrunning logic

---

## 6. High-Level Architecture (Conceptual)

The system consists of the following logical components:

- **Data Ingestion**  
  Price data collection from multiple sources (mock / API)

- **Opportunity Detection**  
  Identification of potential arbitrage opportunities

- **Risk Evaluation Engine**  
  Evaluation of fees, latency, liquidity, volatility

- **Reserve & Allocation Engine (Simulated)**  
  Reserve-based thresholds  
  Allocation and exposure heuristics

- **Decision Engine**  
  Blocks or allows simulation (GO / NO-GO)

- **Simulation Engine**  
  Executes trade simulation if allowed

- **Analytics & Visualization**  
  Risk vs outcome analysis and reporting

Key principle:

> Decisions are made **before simulation**, not after.

---

## 6.1 Key Assumptions

- Price data is not real-time and may be delayed
- Data sources may be mocked or incomplete
- Risk models are simplified
- Reserve logic is heuristic-based
- The goal is decision logic demonstration, not accuracy

---

## 6.2 Roles & Ownership

Roles define **primary responsibility**, not exclusive control.

### System & Architecture Lead
**Henri**

- system architecture
- scope control
- backend core design
- decision pipeline structure
- reserve & allocation framework

**Technologies:**  
Java, Spring Boot, REST, Docker

---

### Backend & Infrastructure
**Markus (primary)**

- backend services
- infrastructure & deployment
- database modeling
- CI/CD and Docker
- reserve & allocation implementation

---

### Frontend & UX
**Carina (primary), Laura (secondary)**

- frontend development
- UI/UX design
- visualization of risk, reserves, decisions
- API integration

**Technologies:**  
React, TypeScript

---

### Analysis & Process
**Ott-Eerik**

- system analysis
- risk model reasoning
- documentation
- process coordination
- structured decision justification

---

### Role Rotation

- Each member has one primary role
- Role rotation is allowed and encouraged
- Ownership responsibility remains clear

---

## 7. Expected Outcome

By the end of the project:

- a working risk-aware simulation platform exists
- system architecture is clearly structured
- decision logic is documented and justified
- team collaboration is demonstrated in a real system context

---

## 7.1 Success Criteria

The project is considered successful if:

- arbitrage opportunities are detected from multiple sources
- risk evaluation blocks trades when appropriate
- simulations run only in allowed cases
- results are analyzable and visualized
- architecture and decisions are documented

---

## 8. Legal & Ethical Disclaimer

This project is educational and technical in nature.  
It does not provide investment advice, perform real trades, or interact with real markets.

---

## 9. Scope Limitation

This project is defined strictly within an academic and experimental context.

While the ideas explored here may inform future independent work, any such continuation is explicitly **out of scope** for this project and this repository.

---

## Appendix A - Project Origin

The project originated from a team challenge to design a **risk-aware arbitrage simulation platform**, focusing on architecture, responsibility separation, and decision logic rather than profit-oriented trading behavior.