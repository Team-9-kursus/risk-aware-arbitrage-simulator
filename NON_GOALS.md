# NON-GOALS
## What Risk aware arbitrage simulator Explicitly Does NOT Do

**Status:** Locked  
**Purpose:** Scope protection and expectation management

---

## 1. No Real Trading

Risk aware arbitrage simulator does **not**:
- execute real trades
- connect to live trading accounts
- interact with real exchanges for execution
- place orders on any market

All trading logic exists **only at simulation level**.

---

## 2. No Real Money

The system:
- does not use real funds
- does not manage real balances
- does not model real user portfolios
- does not expose financial risk to users

All capital, reserves, and balances are **fully simulated**.

---

## 3. No Financial Advice

Risk aware arbitrage simulator:
- does not provide investment advice
- does not suggest trades
- does not recommend strategies
- does not evaluate user profitability

The system evaluates **system behavior**, not user outcomes.

---

## 4. No Profit Optimization

The platform:
- does not aim to maximize profit
- does not optimize ROI
- does not tune strategies for returns
- does not perform parameter optimization

The focus is on **risk-aware decision logic**, not performance metrics.

---

## 5. No Advanced Trading Strategies

Risk aware arbitrage simulator intentionally excludes:
- triangular arbitrage
- derivatives (futures, options)
- leveraged trading
- margin trading
- high-frequency trading (HFT)
- MEV or frontrunning strategies

---

## 6. No Real-Time Guarantees

The system:
- does not guarantee real-time data accuracy
- does not operate under low-latency assumptions
- does not model exchange-level execution engines

Price data may be delayed, mocked, or incomplete.

---

## 7. No Production Certification

Risk aware arbitrage simulator:
- is not production-hardened
- is not security-audited
- is not stress-tested at scale
- is not compliant with financial regulations

The system is **educational**, not enterprise-grade.

---

## 8. No User Risk Management

The platform:
- does not manage user risk profiles
- does not provide personalized thresholds
- does not adapt behavior based on user financial data

All thresholds are **system-level and predefined**.

---

## 9. No Hidden Scope Expansion

Any feature not explicitly documented in:
- `PROJECT_BRIEF.md`
- `ARCHITECTURE.md`
- `/docs`

is considered **out of scope**.

---

## 10. Why These Non-Goals Exist

These non-goals exist to:
- prevent scope creep
- maintain architectural clarity
- ensure educational focus
- avoid ethical and legal risks
- support responsible system design

---

## Final Note

> A well-designed system is defined not only by what it does,  
> but by what it **explicitly refuses to do**.
> Commercial deployment or production use within this repository

Risk aware arbitrage simulator boundaries are intentional and enforced.