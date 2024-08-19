package main.java.com.nursery.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderPage extends JFrame {
    private final JFrame frame;
    private final JComboBox<String> productComboBox;
    private final JTextField quantityField;
    private final JTextField priceField;
    private final JTextField totalField;
    private final DefaultListModel<String> productListModel;

    private double total = 0.0;
    private final List<String> productListData = new ArrayList<String>();
    private final Map<String, Double> productPrices = new HashMap<>();

    public OrderPage() {
        productPrices.put("Marigold", 250.00);
        productPrices.put("Chilli", 100.00);
        productPrices.put("Cabbage", 80.00);
        productPrices.put("Brinjal", 60.00);
        productPrices.put("Califlower", 80.00);
        productPrices.put("Capsicum", 200.00);
        productPrices.put("Cucumber", 100.00);
        productPrices.put("Tomato", 160.00);

        frame = new JFrame("Product Order ");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Product:"));
        inputPanel.add(productComboBox);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(addButton);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.add(new JLabel("Cart:"), BorderLayout.NORTH);
        listPanel.add(new JScrollPane(productList), BorderLayout.CENTER);

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new GridLayout(2, 1));
        totalPanel.add(new JLabel("Total:"));
        totalPanel.add(totalField);
        totalPanel.add(checkoutButton);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(listPanel, BorderLayout.CENTER);
        frame.getContentPane().add(totalPanel, BorderLayout.SOUTH);

        productComboBox.addActionListener(new ProductComboBoxListener());
        addButton.addActionListener(new AddButtonListener());
        checkoutButton.addActionListener(new CheckoutButtonListener());

        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setMaximumSize(new Dimension(1600, 1200));
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
            if(quantity!=0) {
                double subtotal = price * quantity;
                total += subtotal;
                productListData.add(productName + ":" + quantity + " x Rs" + price + " - Rs" + subtotal);
                productListModel.addElement(productName + ":" + quantity + " x Rs" + price + " - Rs" + subtotal);
                totalField.setText(String.format("Rs %.2f", total));
                quantityField.setText("");
            }
            else {
                JOptionPane.showMessageDialog(null,"Invalid quantity");
            }

            try{
                double subtotal = 0;
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/PlantNursery","root","9008496759s");
                String query = "INSERT INTO Orders (productName, productPrice, productQuantity, productTotal) VALUES(?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, productName);
                ps.setDouble(2, price);
                ps.setInt(3, quantity);
                ps.setDouble(4, subtotal);
                ps.executeUpdate();
                if(quantity!=0) {
                    JOptionPane.showMessageDialog(null,"Added to cart");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Enter a valid quantity");
                }
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
