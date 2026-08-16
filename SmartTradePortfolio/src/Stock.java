import java.io.Serializable;

public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String symbol;
    private final String company;
    private final String sector;
    private double price;
    private double previousPrice;
    private double dayOpen;
    private double dayHigh;
    private double dayLow;

    public Stock(String symbol, String company, String sector, double price) {
        this.symbol = symbol;
        this.company = company;
        this.sector = sector;
        this.price = price;
        this.previousPrice = price;
        this.dayOpen = price;
        this.dayHigh = price;
        this.dayLow = price;
    }

    public String getSymbol() { return symbol; }
    public String getCompany() { return company; }
    public String getSector() { return sector; }
    public double getPrice() { return price; }
    public double getPreviousPrice() { return previousPrice; }
    public double getDayOpen() { return dayOpen; }
    public double getDayHigh() { return dayHigh; }
    public double getDayLow() { return dayLow; }

    public void updatePrice(double newPrice) {
        previousPrice = price;
        price = Math.max(1.0, newPrice);
        dayHigh = Math.max(dayHigh, price);
        dayLow = Math.min(dayLow, price);
    }

    public double changePercent() {
        return previousPrice == 0 ? 0 : ((price - previousPrice) / previousPrice) * 100.0;
    }

    public double dayChangePercent() {
        return dayOpen == 0 ? 0 : ((price - dayOpen) / dayOpen) * 100.0;
    }
}
