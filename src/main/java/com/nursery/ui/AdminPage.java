package main.java.com.nursery.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame {

    public AdminPage() {
        setTitle("Admin Dashboard - Nandishwara Nursery");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBackground(new Color(99, 62, 24));

        JLabel titleLabel = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setOpaque(false);

        JButton manageProductsButton = createButton("Manage Products", "path_to_your_icon.png");
        JButton viewOrdersButton = createButton("View Order History", "path_to_your_icon.png");

        buttonPanel.add(manageProductsButton);
        buttonPanel.add(viewOrdersButton);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setOpaque(false);
        containerPanel.add(buttonPanel, BorderLayout.CENTER);

        containerPanel.add(Box.createHorizontalGlue(), BorderLayout.EAST);
        containerPanel.add(Box.createHorizontalGlue(), BorderLayout.WEST);

        backgroundPanel.add(containerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        JButton exitButton = createButton("Exit", "path_to_your_icon.png");
        bottomPanel.add(exitButton);

        manageProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomePage manageProductsPage = new HomePage();
                manageProductsPage.setVisible(true);
            }
        });

        viewOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrderHistoryPage viewOrdersPage = new OrderHistoryPage();
                viewOrdersPage.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(backgroundPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setIcon(new ImageIcon(iconPath));
        button.setIconTextGap(10);
        button.setFont(new Font("Serif", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(new Color(205, 133, 63));
        button.setForeground(Color.BLACK);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminPage::new);
    }
}
