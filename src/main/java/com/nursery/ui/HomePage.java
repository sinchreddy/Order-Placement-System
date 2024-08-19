package main.java.com.nursery.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {

    public HomePage() {
        setTitle("Nandishwara Nursery");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBackground(new Color(99, 62, 24)); // Light brown background

        JLabel titleLabel = new JLabel("Nandishwara Nursery", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.setOpaque(false);

        JButton productsButton = createButton("Products", "path_to_your_icon.png");
        JButton orderButton = createButton("Order", "path_to_your_icon.png");
        JButton adminButton = createButton("Admin ", "path_to_your_icon.png");
        JButton exitButton = createButton("Exit", "path_to_your_icon.png");

        buttonPanel.add(productsButton);
        buttonPanel.add(orderButton);
        buttonPanel.add(adminButton);
        buttonPanel.add(exitButton);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setOpaque(false);
        containerPanel.add(buttonPanel, BorderLayout.CENTER);

        containerPanel.add(Box.createHorizontalGlue(), BorderLayout.EAST);
        containerPanel.add(Box.createHorizontalGlue(), BorderLayout.WEST);

        backgroundPanel.add(containerPanel, BorderLayout.CENTER);

        productsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductsPage productsPage = new ProductsPage();
                productsPage.setVisible(true);
            }
        });

        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrderPage orderPage = new OrderPage();
                orderPage.setVisible(true);
            }
        });

        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adminlogin admin = new adminlogin();
                admin.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Thankyou for visiting!");
                System.exit(0);
            }
        });

        add(backgroundPanel);
        setVisible(true);
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setIcon(new ImageIcon(iconPath));
        button.setIconTextGap(10);
        button.setFont(new Font("Serif", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(50,50));
        button.setBackground(new Color(205, 133, 63));
        button.setForeground(Color.BLACK);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }
}