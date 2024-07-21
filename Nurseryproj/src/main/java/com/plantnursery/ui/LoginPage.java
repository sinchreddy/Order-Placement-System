package main.java.com.plantnursery.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class LoginPage extends JFrame {
    private final JTextField name;
    private final JTextField email;
    private final JPasswordField password;

    public LoginPage() {
        setTitle("NANDISHWARA NURSERY");
        setSize(900, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set the background color
        getContentPane().setBackground(new Color(35, 127, 184)); // Light brown background


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(35, 127, 184)); // Light brown background
        panel.setBounds(50, 50, 300, 350);
        add(panel);

        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 0, 0)); // Dark brown text
        titleLabel.setBounds(40, 20, 220, 30);
        panel.add(titleLabel);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setForeground(new Color(0, 0, 0)); // Dark brown text
        nameLabel.setBounds(30, 70, 80, 25);
        panel.add(nameLabel);

        name = new JTextField();
        name.setBackground(new Color(245, 245, 220)); // Beige background
        name.setForeground(new Color(0, 0, 0)); // Dark brown text
        name.setBounds(30, 100, 240, 30);
        panel.add(name);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setForeground(new Color(0, 0, 0)); // Dark brown text
        emailLabel.setBounds(30, 140, 80, 25);
        panel.add(emailLabel);

        email = new JTextField();
        email.setBackground(new Color(245, 245, 220)); // Beige background
        email.setForeground(new Color(0, 0, 0)); // Dark brown text
        email.setBounds(30, 170, 240, 30);
        panel.add(email);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(new Color(0, 0, 0)); // Dark brown text
        passwordLabel.setBounds(30, 210, 80, 25);
        panel.add(passwordLabel);

        password = new JPasswordField();
        password.setBackground(new Color(245, 245, 220)); // Beige background
        password.setForeground(new Color(0, 0, 0)); // Dark brown text
        password.setBounds(30, 240, 240, 30);
        panel.add(password);

        JButton signUp = new JButton("Sign Up");
        signUp.setBackground(new Color(99, 62, 24)); // Brown button
        signUp.setForeground(new Color(0, 0, 0)); // White text
        signUp.setBounds(30, 290, 240, 30);
        panel.add(signUp);


        ImageIcon i1 = new ImageIcon(getClass().getResource("images/cauli.jpg"));
        JLabel imageLabel = new JLabel(i1);
        int imageSize = 100;
        add(imageLabel, BorderLayout.WEST);
        setVisible(true);

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameText = name.getText();
                String emailText = email.getText();
                String passwordText = new String(password.getPassword());

                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/PlantNursery","root","9008496759s");
                    String query = "INSERT INTO Users VALUES (?,?,?)";
                    PreparedStatement ps=connection.prepareStatement(query);
                    ps.setString(1, nameText);
                    ps.setString(2, emailText);
                    ps.setString(3, passwordText);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Login successful");
                    connection.close();
                }
                catch(Exception exception){
                    JOptionPane.showMessageDialog(null,exception);
                }
                // Add your registration logic here
                JOptionPane.showMessageDialog(null, "Account created successfully!");

                HomePage homePage = new HomePage();
                homePage.setVisible(true);
                LoginPage.this.setVisible(false);

            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }
}