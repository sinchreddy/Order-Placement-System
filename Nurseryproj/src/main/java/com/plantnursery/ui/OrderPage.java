package main.java.com.plantnursery.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OrderPage extends JFrame {
    // GUI components
    private final JFrame frame;
    private final JComboBox<String> productComboBox;
    private final JTextField quantityField;
    private final JTextField priceField;
    private final JTextField totalField;
    private final DefaultListModel<String> productListModel;

    // Data storage
    private double total = 0.0;
    private final java.util.List<String> productListData = new java.util.ArrayList<String>();
    private final java.util.Map<String, Double> productPrices = new java.util.HashMap<>();

    public OrderPage() {
        // Initialize product prices
        productPrices.put("Marigold", 250.00);
        productPrices.put("Chilli", 100.00);
        productPrices.put("Cabbage", 80.00);
        productPrices.put("Brinjal", 60.00);
        productPrices.put("Califlower", 80.00);
        productPrices.put("Capsicum", 200.00);
        productPrices.put("Cucumber", 100.00);
        productPrices.put("Tomato", 160.00);

        // Create GUI components
        frame = new JFrame("Product Order ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 800, 600);

        productComboBox = new JComboBox<>(productPrices.keySet().toArray(new String[0]));
        quantityField = new JTextField(5);
        priceField = new JTextField(10);
        priceField.setEditable(false);
        totalField = new JTextField(10);
        totalField.setEditable(false);

        JButton addButton = new JButton("Add to Cart");
        JButton checkoutButton = new JButton("Checkout");

        productListModel = new DefaultListModel<>();
        JList<String> productList = new JList<>(productListModel);

        // Create panel for product input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Product:"));
        inputPanel.add(productComboBox);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(addButton);

        // Create panel for product list
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.add(new JLabel("Cart:"), BorderLayout.NORTH);
        listPanel.add(new JScrollPane(productList), BorderLayout.CENTER);

        // Create panel for total and checkout
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new GridLayout(2, 1));
        totalPanel.add(new JLabel("Total:"));
        totalPanel.add(totalField);
        totalPanel.add(checkoutButton);

        // Add components to frame
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(listPanel, BorderLayout.CENTER);
        frame.getContentPane().add(totalPanel, BorderLayout.SOUTH);

        // Add event listeners
        productComboBox.addActionListener(new ProductComboBoxListener());
        addButton.addActionListener(new AddButtonListener());
        checkoutButton.addActionListener(new CheckoutButtonListener());

        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(800, 600)); // Set the minimum size of the frame to 800x600
        frame.setMaximumSize(new Dimension(1600, 1200)); // Set the maximum size of the frame to 1600x1200
        // Set up frame
        frame.pack();
        frame.setVisible(true);
    }
    private class ProductComboBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String productName = (String) productComboBox.getSelectedItem();
            double price = productPrices.get(productName);
            priceField.setText(String.format("Rs %.2f", price));
        }
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String productName = (String) productComboBox.getSelectedItem();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = productPrices.get(productName);
            double subtotal = price * quantity;
            total += subtotal;
            productListData.add(productName+ ":" + quantity + " x Rs" + price + " - Rs" + subtotal);
            productListModel.addElement(productName+ ":" + quantity + " x Rs" + price + " - Rs" + subtotal);
            totalField.setText(String.format("Rs %.2f", total));
            quantityField.setText("");

            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/PlantNursery","root","9008496759s");
                String query = "INSERT INTO Orders (productName, productPrice, productQuantity, productTotal) VALUES(?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, productName);
                ps.setDouble(2, price);
                ps.setInt(3, quantity);
                ps.setDouble(4, subtotal); // subtotal is the productTotal value
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null,"Added to history");
                connection.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null,exception);
            }
        }
    }

    private class CheckoutButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (total > 0) {
                JOptionPane.showMessageDialog(frame, "Thank you for your order! Total: Rs" + total);
                total = 0;
                productListData.clear();
                productListModel.clear();
                totalField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Your cart is empty!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OrderPage();
            }
        });
    }
}