import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Trade implements Serializable {
    private static final long serialVersionUID = 1L;
    private final LocalDateTime time;
    private final String type;
    private final String symbol;
    private final int quantity;
    private final double price;
    private final double total;

    public Trade(String type, String symbol, int quantity, double price) {
        this.time = LocalDateTime.now();
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;
    }

    public LocalDateTime getTime() { return time; }
    public String getType() { return type; }
    public String getSymbol() { return symbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public double getTotal() { return total; }

    public String displayTime() {
        return time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
