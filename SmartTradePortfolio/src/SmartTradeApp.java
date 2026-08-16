import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SmartTradeApp extends JFrame {

    private double cash = 100000;
    private double portfolioValue = 100000;
    private final List<String> holdings = new ArrayList<>();

    private JLabel cashLabel;
    private JLabel portfolioLabel;
    private JLabel profitLabel;
    private JLabel riskLabel;
    private JTextArea activityArea;

    // SmartTrade color theme
    private final Color PURPLE = new Color(103, 58, 183);
    private final Color DARK_PURPLE = new Color(74, 42, 130);
    private final Color LIGHT_PURPLE = new Color(248, 245, 255);
    private final Color GREEN = new Color(39, 150, 100);
    private final Color RED = new Color(210, 70, 80);
    private final Color GOLD = new Color(220, 170, 55);
    private final Color CARD = Color.WHITE;
    private final Color TEXT = new Color(45, 45, 55);

    public SmartTradeApp() {

        setTitle("SmartTrade | Personal Investment Simulator");
        setSize(1100, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buildUI();
    }

    private void buildUI() {

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(LIGHT_PURPLE);

        // ================= HEADER =================

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PURPLE);
        header.setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("SMARTTRADE");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 27));

        JLabel subtitle = new JLabel(
                "  Personal Investment Intelligence Platform"
        );
        subtitle.setForeground(new Color(235, 225, 255));
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));

        JPanel titleBox = new JPanel(new GridLayout(2, 1));
        titleBox.setOpaque(false);
        titleBox.add(title);
        titleBox.add(subtitle);

        JButton refreshButton = new JButton("⟳ Refresh Market");
        styleButton(refreshButton, Color.WHITE, PURPLE);

        header.add(titleBox, BorderLayout.WEST);
        header.add(refreshButton, BorderLayout.EAST);

        main.add(header, BorderLayout.NORTH);

        // ================= SUMMARY CARDS =================

        JPanel cards = new JPanel(new GridLayout(1, 4, 16, 16));
        cards.setBackground(LIGHT_PURPLE);
        cards.setBorder(new EmptyBorder(20, 20, 12, 20));

        cashLabel = createValueLabel("₹100,000");
        portfolioLabel = createValueLabel("₹100,000");
        profitLabel = createValueLabel("₹0");
        riskLabel = createValueLabel("LOW");

        cards.add(createCard("AVAILABLE CASH", cashLabel, PURPLE));
        cards.add(createCard("PORTFOLIO VALUE", portfolioLabel, GOLD));
        cards.add(createCard("PROFIT / LOSS", profitLabel, GREEN));
        cards.add(createCard("RISK LEVEL", riskLabel, RED));

        // ================= CENTER =================

        JPanel center = new JPanel(new GridLayout(1, 2, 18, 18));
        center.setBackground(LIGHT_PURPLE);
        center.setBorder(new EmptyBorder(8, 20, 18, 20));

        // ================= MARKET PANEL =================

        JPanel marketPanel = new JPanel(new BorderLayout());
        marketPanel.setBackground(CARD);
        marketPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 210, 235)
                        ),
                        new EmptyBorder(8, 8, 8, 8)
                )
        );

        JLabel marketTitle = new JLabel("  📈 MARKET WATCH");
        marketTitle.setForeground(DARK_PURPLE);
        marketTitle.setFont(new Font("Arial", Font.BOLD, 18));
        marketTitle.setBorder(new EmptyBorder(10, 5, 12, 5));

        String[] columns = {
                "STOCK",
                "PRICE",
                "CHANGE",
                "SIGNAL"
        };

        Object[][] data = {
                {"NOVA", "₹2,492", "+2.8%", "BUY"},
                {"AURX", "₹1,845", "-1.2%", "HOLD"},
                {"MEDI", "₹3,120", "+4.5%", "BUY"},
                {"GRNF", "₹1,276", "+0.7%", "HOLD"},
                {"FINX", "₹2,730", "-2.1%", "CAUTION"}
        };

        JTable table = new JTable(data, columns);

        table.setRowHeight(35);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setForeground(TEXT);
        table.setGridColor(new Color(235, 230, 240));

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        table.getTableHeader().setBackground(
                new Color(238, 230, 250)
        );

        table.getTableHeader().setForeground(DARK_PURPLE);

        JScrollPane tableScroll = new JScrollPane(table);

        marketPanel.add(marketTitle, BorderLayout.NORTH);
        marketPanel.add(tableScroll, BorderLayout.CENTER);

        // ================= TRADE PANEL =================

        JPanel tradePanel = new JPanel(new BorderLayout());
        tradePanel.setBackground(CARD);

        tradePanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 210, 235)
                        ),
                        new EmptyBorder(8, 8, 8, 8)
                )
        );

        JLabel tradeTitle = new JLabel("  💹 QUICK TRADE");
        tradeTitle.setForeground(DARK_PURPLE);
        tradeTitle.setFont(new Font("Arial", Font.BOLD, 18));
        tradeTitle.setBorder(new EmptyBorder(10, 5, 12, 5));

        JPanel tradeForm = new JPanel(
                new GridLayout(5, 2, 12, 12)
        );

        tradeForm.setBackground(CARD);
        tradeForm.setBorder(
                new EmptyBorder(15, 15, 15, 15)
        );

        JLabel stockText = new JLabel("Select Stock");
        JLabel quantityText = new JLabel("Quantity");
        JLabel actionText = new JLabel("Action");

        JComboBox<String> stockBox =
                new JComboBox<>(
                        new String[]{
                                "NOVA",
                                "AURX",
                                "MEDI",
                                "GRNF",
                                "FINX"
                        }
                );

        JSpinner quantitySpinner =
                new JSpinner(
                        new SpinnerNumberModel(
                                1,
                                1,
                                100,
                                1
                        )
                );

        JComboBox<String> actionBox =
                new JComboBox<>(
                        new String[]{
                                "BUY",
                                "SELL"
                        }
                );

        JButton executeButton =
                new JButton("EXECUTE TRADE");

        JButton portfolioButton =
                new JButton("VIEW PORTFOLIO");

        styleButton(
                executeButton,
                GREEN,
                Color.WHITE
        );

        styleButton(
                portfolioButton,
                PURPLE,
                Color.WHITE
        );

        tradeForm.add(stockText);
        tradeForm.add(stockBox);

        tradeForm.add(quantityText);
        tradeForm.add(quantitySpinner);

        tradeForm.add(actionText);
        tradeForm.add(actionBox);

        tradeForm.add(executeButton);
        tradeForm.add(portfolioButton);

        activityArea = new JTextArea();

        activityArea.setEditable(false);
        activityArea.setFont(
                new Font("Monospaced", Font.PLAIN, 12)
        );

        activityArea.setBackground(
                new Color(250, 248, 253)
        );

        activityArea.setForeground(TEXT);

        activityArea.setText(
                "SMARTTRADE ACTIVITY\n" +
                "====================\n" +
                "Market ready.\n" +
                "Select a stock and execute a trade."
        );

        JScrollPane activityScroll =
                new JScrollPane(activityArea);

        tradePanel.add(tradeTitle, BorderLayout.NORTH);
        tradePanel.add(tradeForm, BorderLayout.CENTER);
        tradePanel.add(activityScroll, BorderLayout.SOUTH);

        center.add(marketPanel);
        center.add(tradePanel);

        // ================= FOOTER =================

        JPanel footer = new JPanel(new BorderLayout());

        footer.setBackground(DARK_PURPLE);
        footer.setBorder(
                new EmptyBorder(10, 20, 10, 20)
        );

        JLabel footerText =
                new JLabel(
                        "SmartTrade • Educational Market Simulator"
                );

        footerText.setForeground(Color.WHITE);

        JButton exportButton =
                new JButton("Export Report");

        JButton resetButton =
                new JButton("Reset Demo");

        styleButton(
                exportButton,
                Color.WHITE,
                DARK_PURPLE
        );

        styleButton(
                resetButton,
                new Color(255, 235, 235),
                RED
        );

        JPanel footerButtons = new JPanel();

        footerButtons.setOpaque(false);

        footerButtons.add(exportButton);
        footerButtons.add(resetButton);

        footer.add(
                footerText,
                BorderLayout.WEST
        );

        footer.add(
                footerButtons,
                BorderLayout.EAST
        );

        // ================= CONTENT =================

        JPanel content =
                new JPanel(new BorderLayout());

        content.setBackground(LIGHT_PURPLE);

        content.add(
                cards,
                BorderLayout.NORTH
        );

        content.add(
                center,
                BorderLayout.CENTER
        );

        main.add(
                content,
                BorderLayout.CENTER
        );

        main.add(
                footer,
                BorderLayout.SOUTH
        );

        // ================= ACTIONS =================

        executeButton.addActionListener(e -> {

            String stock =
                    stockBox.getSelectedItem().toString();

            int quantity =
                    (Integer) quantitySpinner.getValue();

            String action =
                    actionBox.getSelectedItem().toString();

            double price =
                    getPrice(stock);

            double total =
                    price * quantity;

            if (action.equals("BUY")) {

                if (cash >= total) {

                    cash -= total;

                    portfolioValue += total;

                    holdings.add(
                            stock + " x" + quantity
                    );

                    activityArea.append(
                            "\nBUY  " +
                            quantity +
                            " " +
                            stock +
                            " @ ₹" +
                            String.format(
                                    "%.2f",
                                    price
                            )
                    );

                    updateDashboard(0);

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Insufficient virtual cash."
                    );
                }

            } else {

                if (!holdings.isEmpty()) {

                    cash += total;

                    portfolioValue -= total;

                    activityArea.append(
                            "\nSELL " +
                            quantity +
                            " " +
                            stock +
                            " @ ₹" +
                            String.format(
                                    "%.2f",
                                    price
                            )
                    );

                    updateDashboard(0);

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No holdings available to sell."
                    );
                }
            }
        });

        refreshButton.addActionListener(e -> {

            double change =
                    (Math.random() * 1000) - 500;

            portfolioValue += change;

            double profit =
                    portfolioValue - 100000;

            updateDashboard(profit);

            activityArea.append(
                    "\nMarket refreshed."
            );
        });

        portfolioButton.addActionListener(e -> {

            StringBuilder message =
                    new StringBuilder();

            message.append(
                    "YOUR PORTFOLIO\n"
            );

            message.append(
                    "====================\n"
            );

            if (holdings.isEmpty()) {

                message.append(
                        "No stocks purchased yet."
                );

            } else {

                for (String item : holdings) {

                    message.append(
                            item
                    );

                    message.append("\n");
                }
            }

            JOptionPane.showMessageDialog(
                    this,
                    message.toString(),
                    "Portfolio",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        resetButton.addActionListener(e -> {

            cash = 100000;
            portfolioValue = 100000;

            holdings.clear();

            updateDashboard(0);

            activityArea.setText(
                    "SMARTTRADE ACTIVITY\n" +
                    "====================\n" +
                    "Demo account reset."
            );
        });

        exportButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Portfolio report prepared successfully.",
                    "Export Report",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        setContentPane(main);
    }

    // ================= BUTTON STYLE =================

    private void styleButton(
            JButton button,
            Color background,
            Color foreground
    ) {

        button.setBackground(background);
        button.setForeground(foreground);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        15,
                        8,
                        15
                )
        );
    }

    private JLabel createValueLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        label.setForeground(TEXT);

        label.setBorder(
                new EmptyBorder(
                        8,
                        12,
                        8,
                        12
                )
        );

        return label;
    }

    private JPanel createCard(
            String title,
            JLabel value,
            Color accent
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(CARD);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                accent,
                                2
                        ),
                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        11
                )
        );

        titleLabel.setForeground(accent);

        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                value,
                BorderLayout.CENTER
        );

        return card;
    }

    private double getPrice(
            String stock
    ) {

        switch (stock) {

            case "NOVA":
                return 2492.47;

            case "AURX":
                return 1845.20;

            case "MEDI":
                return 3120.00;

            case "GRNF":
                return 1276.50;

            case "FINX":
                return 2730.75;

            default:
                return 1000;
        }
    }

    private void updateDashboard(
            double profit
    ) {

        cashLabel.setText(
                String.format(
                        "₹%.2f",
                        cash
                )
        );

        portfolioLabel.setText(
                String.format(
                        "₹%.2f",
                        portfolioValue
                )
        );

        if (profit >= 0) {

            profitLabel.setForeground(
                    GREEN
            );

            profitLabel.setText(
                    "+₹" +
                    String.format(
                            "%.2f",
                            profit
                    )
            );

        } else {

            profitLabel.setForeground(
                    RED
            );

            profitLabel.setText(
                    "-₹" +
                    String.format(
                            "%.2f",
                            Math.abs(profit)
                    )
            );
        }

        if (profit > 5000) {

            riskLabel.setText("MEDIUM");
            riskLabel.setForeground(GOLD);

        } else {

            riskLabel.setText("LOW");
            riskLabel.setForeground(GREEN);
        }
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(() -> {

            SmartTradeApp app =
                    new SmartTradeApp();

            app.setVisible(true);
        });
    }
}