package main.java.com.plantnursery.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HomePage extends JFrame {

    public HomePage() {
        // Set up the frame
        setTitle("Nandishwara Nursery");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a custom panel with a background color
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBackground(new Color(99, 62, 24)); // Light brown background

        // Create and set the title
        JLabel titleLabel = new JLabel("Nandishwara Nursery", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.setOpaque(false);

        // Create buttons with icons
        JButton productsButton = createButton("Products", "path_to_your_icon.png");
        JButton orderButton = createButton("Order", "path_to_your_icon.png");
        JButton orderHistoryButton = createButton("Order History", "path_to_your_icon.png");
        JButton exitButton = createButton("Exit", "path_to_your_icon.png");

        // Add buttons to panel
        buttonPanel.add(productsButton);
        buttonPanel.add(orderButton);
        buttonPanel.add(orderHistoryButton);
        buttonPanel.add(exitButton);

        // Create a container panel to align buttons to the center
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setOpaque(false);
        containerPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add a horizontal glue to center the buttons
        containerPanel.add(Box.createHorizontalGlue(), BorderLayout.EAST);
        containerPanel.add(Box.createHorizontalGlue(), BorderLayout.WEST);

        // Add container panel to background panel
        backgroundPanel.add(containerPanel, BorderLayout.CENTER);

        // Add action listeners for buttons
        productsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle Products button click
                JOptionPane.showMessageDialog(null, "Products button clicked");
                ProductsPage productsPage = new ProductsPage();
                productsPage.setVisible(true);
            }
        });

        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle Order button click
                JOptionPane.showMessageDialog(null, "Order button clicked");
                OrderPage orderPage = new OrderPage();
                orderPage.setVisible(true);
            }
        });

        orderHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle Order History button click
                JOptionPane.showMessageDialog(null, "Order History button clicked");
                OrderHistoryPage orderHistoryPage = new OrderHistoryPage();
                orderHistoryPage.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle Exit button click
                System.exit(0);
            }
        });

        // Add background panel to frame
        add(backgroundPanel);
        // Set the frame visible
        setVisible(true);
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setIcon(new ImageIcon(iconPath)); // Provide the path to your icon
        button.setIconTextGap(10); // Space between icon and text
        button.setFont(new Font("Serif", Font.BOLD, 16)); // Decrease font size
        button.setPreferredSize(new Dimension(50,50)); // Decrease button size
        button.setBackground(new Color(205, 133, 63)); // Brown button background
        button.setForeground(Color.BLACK); // Black button text
        return button;
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }
}