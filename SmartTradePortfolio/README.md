# SmartTrade — Personal Stock Market Simulator

A Java Swing mini-project created for an internship portfolio. It simulates a small stock market and focuses on learning-oriented portfolio analytics rather than real trading.

## Features
- Simulated market price movement
- Virtual cash wallet
- Buy and sell operations
- Portfolio value and unrealized P/L
- Average-cost tracking
- Risk score based on concentration and price movement
- Investor health label
- Market mood indicator
- Momentum/caution educational signals
- Search/filter stocks
- Trade history
- CSV portfolio export
- Local persistence using Java serialization
- Reset demo account

## Tech Stack
- Java
- Swing
- OOP
- Collections
- File I/O / Serialization
- CSV export
- Timer-based simulation

## Run
Open the `src` folder in VS Code and run `SmartTradeApp.java`.

Or from a terminal inside `src`:

```bash
javac *.java
java SmartTradeApp
```

The program creates `smarttrade_data.dat` for local demo data and `smarttrade_portfolio.csv` when exporting.

> Educational simulator only. It does not connect to a real exchange or provide financial advice.
