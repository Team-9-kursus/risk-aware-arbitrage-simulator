# Arbitrage – Basics (Team-9)

This document explains **what arbitrage is**, **why it exists**, and **how it relates to this project**.
It is intended for team members who are **new to arbitrage concepts**.

---

## What is Arbitrage?

**Arbitrage** is the practice of exploiting **price differences of the same asset across different markets**.

Simple example:
- BTC price on Exchange A: €30,000
- BTC price on Exchange B: €30,200
- Buy on A, sell on B, profit = €200 (before costs)

Arbitrage exists because:
- Markets are fragmented
- Prices update at different speeds
- Liquidity varies across venues

---

## What is an Arbitrage Bot?

An **arbitrage bot** is a system that:
1. Observes prices across markets
2. Detects arbitrage opportunities
3. Evaluates **risk, fees, latency, and capital exposure**
4. Decides **whether executing the trade is justified**

In real life, most detected opportunities are **not worth executing** due to risk.

---

## Why Arbitrage is Risky (Important)

Common risks:
- Fees eliminate profit
- Latency causes price drift
- Liquidity disappears
- Capital exposure limits
- Partial execution risk

This is why **risk-aware decision logic** is critical.

---

## How This Project Uses Arbitrage

This project **does NOT execute real trades**.

Instead, it:
- Simulates arbitrage scenarios
- Evaluates risk before execution
- Focuses on **decision quality**, not profit
- Runs entirely in a **simulation environment**

Key outcome:
> The system decides **whether NOT to trade**.

---

## Educational Scope

✔ Simulation only  
✔ No real money  
✔ No exchange integrations  
✔ Focus on architecture, risk modeling, and decision logic  

---

## Recommended Learning Resources

### YouTube (Intro Level)

- [YouTube link](https://www.youtube.com/watch?v=AuCH7fHZsZ4)
- [YouTube link](https://www.youtube.com/watch?v=MhwrQpXQq4I)

(Team members are encouraged to add useful resources.)

---

## Relation to Project Architecture

Arbitrage logic appears in:
- Opportunity detection
- Risk evaluation
- Reserve and allocation modeling
- Decision engine

Details are documented in:
- `ARCHITECTURE.md`
- `risk-model.md`
