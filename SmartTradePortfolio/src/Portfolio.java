import java.io.*;
import java.util.*;

public class Portfolio implements Serializable {
    private static final long serialVersionUID = 1L;
    private double cash = 100000.00;
    private final Map<String, Integer> holdings = new LinkedHashMap<>();
    private final Map<String, Double> averageCost = new LinkedHashMap<>();
    private final List<Trade> trades = new ArrayList<>();

    public double getCash() { return cash; }
    public Map<String, Integer> getHoldings() { return holdings; }
    public Map<String, Double> getAverageCost() { return averageCost; }
    public List<Trade> getTrades() { return trades; }

    public boolean buy(Stock stock, int qty) {
        if (qty <= 0) return false;
        double total = stock.getPrice() * qty;
        if (total > cash) return false;
        int oldQty = holdings.containsKey(stock.getSymbol()) ? holdings.get(stock.getSymbol()) : 0;
        double oldCost = averageCost.containsKey(stock.getSymbol()) ? averageCost.get(stock.getSymbol()) : 0.0;
        double newAvg = ((oldQty * oldCost) + total) / (oldQty + qty);
        cash -= total;
        holdings.put(stock.getSymbol(), oldQty + qty);
        averageCost.put(stock.getSymbol(), newAvg);
        trades.add(new Trade("BUY", stock.getSymbol(), qty, stock.getPrice()));
        return true;
    }

    public boolean sell(Stock stock, int qty) {
        if (qty <= 0) return false;
        int owned = holdings.containsKey(stock.getSymbol()) ? holdings.get(stock.getSymbol()) : 0;
        if (qty > owned) return false;
        double total = stock.getPrice() * qty;
        cash += total;
        int remaining = owned - qty;
        if (remaining == 0) {
            holdings.remove(stock.getSymbol());
            averageCost.remove(stock.getSymbol());
        } else {
            holdings.put(stock.getSymbol(), remaining);
        }
        trades.add(new Trade("SELL", stock.getSymbol(), qty, stock.getPrice()));
        return true;
    }

    public double marketValue(Map<String, Stock> stocks) {
        double value = cash;
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            Stock s = stocks.get(entry.getKey());
            if (s != null) value += s.getPrice() * entry.getValue();
        }
        return value;
    }

    public double investedValue(Map<String, Stock> stocks) {
        double value = 0;
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            Double avg = averageCost.get(entry.getKey());
            if (avg != null) value += avg * entry.getValue();
        }
        return value;
    }

    public double unrealizedPnL(Map<String, Stock> stocks) {
        double pnl = 0;
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            Stock s = stocks.get(entry.getKey());
            Double avg = averageCost.get(entry.getKey());
            if (s != null && avg != null) pnl += (s.getPrice() - avg) * entry.getValue();
        }
        return pnl;
    }

    public double riskScore(Map<String, Stock> stocks) {
        if (holdings.isEmpty()) return 0;
        double total = 0;
        double concentration = 0;
        double value = marketValue(stocks) - cash;
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            Stock s = stocks.get(entry.getKey());
            if (s == null) continue;
            double holdingValue = s.getPrice() * entry.getValue();
            double weight = value == 0 ? 0 : holdingValue / value;
            concentration += weight * weight;
            total += Math.abs(s.dayChangePercent()) * weight;
        }
        double score = Math.min(100, concentration * 55 + Math.min(45, total * 3));
        return score;
    }

    public String investorHealth(Map<String, Stock> stocks) {
        double score = riskScore(stocks);
        if (score < 25) return "Healthy";
        if (score < 55) return "Watchlist";
        return "High Risk";
    }

    public void save(File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this);
        }
    }

    public static Portfolio load(File file) {
        if (!file.exists()) return new Portfolio();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (Portfolio) in.readObject();
        } catch (Exception e) {
            return new Portfolio();
        }
    }
}
