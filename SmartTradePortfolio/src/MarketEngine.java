import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class MarketEngine {
    private final Map<String, Stock> stocks = new LinkedHashMap<>();
    private final Random random = new Random();
    private double marketMood = 0.0;

    public MarketEngine() {
        add("NOVA", "Nova Systems", "Technology", 2480.00);
        add("AURX", "Aurora Energy", "Energy", 1425.00);
        add("MEDI", "MediCore Labs", "Healthcare", 980.00);
        add("GRNF", "GreenField Foods", "Consumer", 620.00);
        add("TRNS", "TransitNext", "Mobility", 775.00);
        add("FINX", "Finexa Bank", "Finance", 1180.00);
        add("CLDA", "CloudAxis", "Technology", 1675.00);
        add("EDUA", "EduNova", "Education", 540.00);
    }

    private void add(String symbol, String company, String sector, double price) {
        stocks.put(symbol, new Stock(symbol, company, sector, price));
    }

    public Map<String, Stock> getStocks() { return stocks; }

    public void simulateTick() {
        marketMood += (random.nextDouble() - 0.5) * 0.18;
        marketMood = Math.max(-1.0, Math.min(1.0, marketMood));
        for (Stock stock : stocks.values()) {
            double noise = (random.nextDouble() - 0.5) * 0.012;
            double sectorBias = sectorBias(stock.getSector());
            double factor = 1.0 + marketMood * 0.0018 + sectorBias + noise;
            stock.updatePrice(stock.getPrice() * factor);
        }
    }

    private double sectorBias(String sector) {
        if ("Technology".equals(sector)) return (random.nextDouble() - 0.5) * 0.0018;
        if ("Energy".equals(sector)) return (random.nextDouble() - 0.5) * 0.0014;
        return (random.nextDouble() - 0.5) * 0.0010;
    }

    public double getMarketMood() { return marketMood; }

    public String moodLabel() {
        if (marketMood > 0.35) return "Bullish";
        if (marketMood < -0.35) return "Cautious";
        return "Balanced";
    }
}
