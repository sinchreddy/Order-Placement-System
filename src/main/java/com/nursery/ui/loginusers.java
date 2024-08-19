package main.java.com.nursery.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class loginusers extends JFrame {
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JLabel statusLabel;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/PlantNursery";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "9008496759s";

    public loginusers() {
        setTitle("User Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        // Create components
        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        statusLabel = new JLabel(" ");

        // Create panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(loginButton);

        // Create panel for status message
        JPanel statusPanel = new JPanel();
        statusPanel.add(statusLabel);

        // Add components to frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.CENTER);
        getContentPane().add(statusPanel, BorderLayout.SOUTH);

        // Add event listener for button
        loginButton.addActionListener(new LoginButtonListener());

        setVisible(true);
    }

    private class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = emailField.getText();
            String password = new String(passwordField.getPassword());

            System.out.println("Attempting login with Name: " + name + " and Password: " + password);

            if (authenticateUser(name, password)) {
                statusLabel.setText("Login successful!");
                new HomePage();
                dispose();
            } else {
                statusLabel.setText("Invalid email or password.");
            }
        }
    }

    private boolean authenticateUser(String name, String password) {
        boolean isAuthenticated = false;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT COUNT(*) FROM Users WHERE Name = ? AND Password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, password);

                JOptionPane.showMessageDialog(null, "Welcome to Nandishwara Nursery! "+name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        System.out.println("Database query result: " + count);
                        if (count > 0) {
                            isAuthenticated = true;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }

        return isAuthenticated;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(loginusers::new);
    }
}
