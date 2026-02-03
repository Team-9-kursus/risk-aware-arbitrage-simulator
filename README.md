```md
# Risk-Aware Arbitrage Simulation Platform

This is an **educational, risk-aware arbitrage simulation platform**.  
The project focuses on **system architecture, risk-based decision logic, and clean software design**, not on profit maximization or real trading.

> **Disclaimer**  
> This project is strictly educational.  
> It does **not** perform real trades, does **not** use real money, and does **not** provide financial advice.

---

## 1. Project Purpose

The goal of this project is to demonstrate how a system can:

- Detect potential arbitrage opportunities
- Evaluate **risk before execution**
- Block simulations when risk exceeds predefined thresholds
- Simulate trades **only when risk is acceptable**

The emphasis is on:
- architecture
- responsibility separation
- decision pipelines
- risk-aware thinking

---

## 2. Scope & Non-Goals

### In Scope
- Spot-market arbitrage (Exchange A vs Exchange B)
- Simulated price feeds (mock or limited API)
- Risk evaluation (fees, latency, liquidity, volatility)
- Reserve-based thresholds
- Deterministic trade simulation
- Result analytics and visualization

### Explicitly Out of Scope
- Live trading
- Real money
- Profit optimization
- Futures, options, derivatives
- Triangular arbitrage
- High-frequency trading (HFT)
- MEV or frontrunning logic

---

## 3. High-Level Architecture

```

Data Ingestion
↓
Opportunity Detection
↓
Risk Evaluation Engine
↓
Reserve & Allocation Engine
↓
Decision Engine (GO / NO-GO)
↓
Simulation Engine
↓
Analytics & Reporting

```

Key design principle:

> **The system decides whether to simulate *before* simulation, not after.**

---

## 4. Repository Structure

```

risk-aware-arbitrage-simulator/
├── backend/                 # Spring Boot backend
│   ├── src/main/java/com/riskaware/arbitrage
│   │   ├── api              # REST controllers + DTOs
│   │   ├── core             # Business logic (risk, reserve, decision)
│   │   ├── simulation       # Trade & PnL simulation
│   │   └── analytics        # Metrics & analysis
│   └── src/main/resources
│       ├── application.yml
│       └── logback-spring.xml
│
├── frontend/                # Frontend application
│   └── src
│       ├── api              # API clients
│       ├── pages            # Route-level pages
│       ├── components       # UI components
│       ├── stores           # State management
│       └── types            # Shared DTO types
│
├── docs/                    # Architecture & decision documentation
│   ├── architecture-justification.md
│   ├── risk-model.md
│   └── scope-boundary.md
│
├── infra/                   # Infrastructure configs
│   ├── nginx
│   └── scripts
│
├── .github                  # CI/CD workflows
├── docker-compose.yml
└── README.md

````

---

## 5. Backend Overview (Spring Boot)

### Key Modules

| Module | Responsibility |
|------|----------------|
| `ingestion` | Price data sources (mock / API) |
| `opportunity` | Arbitrage detection |
| `risk` | Risk scoring & risk factors |
| `reserve` | Reserve models & exposure limits |
| `allocation` | Capital allocation strategies |
| `decision` | Final GO / NO-GO logic |
| `simulation` | Trade & PnL simulation |
| `analytics` | Metrics & outcome analysis |

---

## 6. Backend – How to Run

### Requirements
- Java **17**
- Maven **3.9+**

### Run Backend
```powershell
cd backend
mvn -DskipTests spring-boot:run
````

### Health Check

```powershell
curl http://localhost:8080/actuator/health
```

Expected response:

```json
{"status":"UP"}
```

---

## 7. Frontend Overview

The frontend visualizes:

* risk levels (Green / Yellow / Red)
* reserve usage
* decision lifecycle
* simulation history

The frontend communicates **only via DTOs**, no business logic duplication.

---

## 8. Frontend – How to Run

```powershell
cd frontend
npm install
npm run dev
```

(Default port depends on tooling; typically `http://localhost:5173` or `3000`.)

---

## 9. Configuration & Environment

* All configuration is **non-secret**
* No real credentials
* No production secrets
* Environment-specific overrides are optional

---

## 10. Documentation

Additional documentation can be found in `/docs`:

* `architecture-justification.md`
* `risk-model.md`
* `scope-boundary.md`

---

## 11. Team & Roles

Roles represent **primary responsibility**, not exclusive ownership.

* **System & Architecture Lead** – Henri
* **Backend & Infrastructure** – Markus
* **Frontend & UX** – Carina (primary), Laura (secondary)
* **Analysis & Process** – Ott-Eerik

Role rotation is allowed and encouraged.

---

## 12. Legal & Ethical Notice

This project is:

* educational
* non-commercial
* non-financial

It must **never** be used for real trading.

---

## 13. Project Status

Current state:

* Architecture locked
* Backend running
* Health check verified
* API endpoints in progress
* Frontend integration ongoing

---

## 14. Academic Scope & Future Continuation

This repository represents an **academic and experimental phase** of the project.

Any future continuation beyond this scope will be conducted:

* in a **separate repository**
* under **separate assumptions and constraints**
* outside the scope of this academic work

This repository should be evaluated **only within its academic context**.

---

**Risk-Aware Arbitrage Simulator**
Risk-aware systems start with saying *no* at the right time.

```