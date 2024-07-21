package main.java.com.plantnursery.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryPage extends JFrame {

    private final List<Order> orderHistory = new ArrayList<>();
    private JTable orderTable;

    public OrderHistoryPage() {
        setTitle("Nandishwara Nursery - Order History");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Navigation Bar
        JMenuBar navigationBar = new JMenuBar();
        JMenu homeMenu = new JMenu("HOME");
        JMenu productMenu = new JMenu("PRODUCT");
        JMenu cartMenu = new JMenu("CART");
        JMenu ordersMenu = new JMenu("ORDERS");
        navigationBar.add(homeMenu);
        navigationBar.add(productMenu);
        navigationBar.add(cartMenu);
        navigationBar.add(ordersMenu);
        setJMenuBar(navigationBar);

        JPanel contentPane = new JPanel(new BorderLayout());

        // Order History Panel
        JPanel orderHistoryPanel = new JPanel(new BorderLayout());
        orderHistoryPanel.setBorder(BorderFactory.createTitledBorder("Order History"));
        contentPane.add(orderHistoryPanel, BorderLayout.CENTER);

        // Order Table
        String[] columnNames = {"Order ID", "PRODUCTS", "PRICE", "QUANTITY", "TOTAL"};
        orderTable = new JTable();
        orderTable.setPreferredScrollableViewportSize(new Dimension(700, 400));
        orderTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        orderHistoryPanel.add(scrollPane, BorderLayout.CENTER);

        JButton loadDataButton = new JButton("Load Data");
        loadDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadOrderData(orderTable);
            }
        });
        orderHistoryPanel.add(loadDataButton, BorderLayout.SOUTH);

        add(contentPane);
        setVisible(true);
    }

    private void loadOrderData(JTable orderTable) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlantNursery", "root", "9008496759s");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Orders";
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            DefaultTableModel model = new DefaultTableModel();
            int col = rsmd.getColumnCount();
            String[] colNames = new String[col];
            for (int i = 0; i < col; i++) {
                colNames[i] = rsmd.getColumnLabel(i + 1);
            }
            model.setColumnIdentifiers(colNames);
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String product = resultSet.getString(2);
                String price = resultSet.getString(3);
                String quantity = resultSet.getString(4);
                String total = resultSet.getString(5);
                String[] row = {id, product, price, quantity, total};
                model.addRow(row);
            }
            orderTable.setModel(model);
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Order class to store order information
    private static class Order {
        public String orderId;
        public String products;
        public double price;
        public int quantity;
        public double total;

        public Order(String orderId, String products, double price, int quantity, double total) {
            this.orderId = orderId;
            this.products = products;
            this.price = price;
            this.quantity = quantity;
            this.total = total;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OrderHistoryPage::new);
    }
}