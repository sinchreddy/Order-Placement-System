package main.java.com.nursery.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class OrderHistoryPage extends JFrame {

    private JTable orderTable;

    public OrderHistoryPage() {
        setTitle("Nandishwara Nursery - Order History");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        JPanel orderHistoryPanel = new JPanel(new BorderLayout());
        orderHistoryPanel.setBorder(BorderFactory.createTitledBorder("Order History"));
        contentPane.add(orderHistoryPanel, BorderLayout.CENTER);

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
                loadOrderData();
            }
        });
        orderHistoryPanel.add(loadDataButton, BorderLayout.SOUTH);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOrderStatus();
            }
        });
        orderHistoryPanel.add(saveButton, BorderLayout.NORTH);

        add(contentPane);
        setVisible(true);
    }

    private void loadOrderData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlantNursery", "root", "9008496759s");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Orders";
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            DefaultTableModel model = new DefaultTableModel();

            int colCount = rsmd.getColumnCount();
            String[] colNames = new String[colCount + 1];

            for (int i = 1; i <= colCount; i++) {
                colNames[i - 1] = rsmd.getColumnName(i);
            }
            colNames[colCount] = "DELIVERED: YES/NO";

            model.setColumnIdentifiers(colNames);

            // Add rows
            while (resultSet.next()) {
                String[] row = new String[colCount + 1];
                for (int i = 1; i <= colCount; i++) {
                    row[i - 1] = resultSet.getString(i);
                }
                boolean delivered = resultSet.getBoolean("delivered");
                row[colCount] = delivered ? "YES" : "NO";
                model.addRow(row);
            }

            orderTable.setModel(model);
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An SQL error occurred: " + e.getMessage());
        }
    }

    private void saveOrderStatus() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlantNursery", "root", "9008496759s");
            String updateQuery = "UPDATE Orders SET delivered = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                String orderId = (String) model.getValueAt(i, 0);
                String deliveredStr = (String) model.getValueAt(i, 6);
                boolean delivered = "YES".equalsIgnoreCase(deliveredStr);

                preparedStatement.setBoolean(1, delivered);
                preparedStatement.setString(2, orderId);

                System.out.println("Updating Order ID: " + orderId + " to DELIVERED: " + delivered);

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            JOptionPane.showMessageDialog(this, "Order statuses updated successfully.");
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An SQL error occurred while updating data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OrderHistoryPage::new);
    }
}
