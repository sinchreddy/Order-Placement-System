package main.java.com.nursery.ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        getContentPane().setBackground(new Color(35, 127, 184));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(35, 127, 184));
        panel.setBounds(50, 50, 300, 350);
        add(panel);

        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 0, 0));
        titleLabel.setBounds(40, 20, 220, 30);
        panel.add(titleLabel);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setForeground(new Color(0, 0, 0));
        nameLabel.setBounds(30, 70, 80, 25);
        panel.add(nameLabel);

        name = new JTextField();
        name.setBackground(new Color(245, 245, 220));
        name.setForeground(new Color(0, 0, 0));
        name.setBounds(30, 100, 240, 30);
        panel.add(name);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setForeground(new Color(0, 0, 0));
        emailLabel.setBounds(30, 140, 80, 25);
        panel.add(emailLabel);

        email = new JTextField();
        email.setBackground(new Color(245, 245, 220));
        email.setForeground(new Color(0, 0, 0));
        email.setBounds(30, 170, 240, 30);
        panel.add(email);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(new Color(0, 0, 0));
        passwordLabel.setBounds(30, 210, 80, 25);
        panel.add(passwordLabel);

        password = new JPasswordField();
        password.setBackground(new Color(245, 245, 220));
        password.setForeground(new Color(0, 0, 0));
        password.setBounds(30, 240, 240, 30);
        panel.add(password);

        JButton signUp = new JButton("Sign Up");
        signUp.setBackground(new Color(99, 62, 24));
        signUp.setForeground(new Color(0, 0, 0));
        signUp.setBounds(30, 290, 120, 30);
        panel.add(signUp);

        JLabel loginLink = new JLabel("Login");
        loginLink.setForeground(Color.BLUE);
        loginLink.setFont(new Font("Arial", Font.BOLD, 14));
        loginLink.setBounds(160, 290, 110, 30);
        panel.add(loginLink);

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
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlantNursery","root","9008496759s");
                    String query = "INSERT INTO Users (name, email, password) VALUES (?,?,?)";
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setString(1, nameText);
                    ps.setString(2, emailText);
                    ps.setString(3, passwordText);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Welcome to Nandishwara Nursery! "+nameText);
                    connection.close();
                } catch(Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }

                HomePage homePage = new HomePage();
                homePage.setVisible(true);
                LoginPage.this.setVisible(false);
            }
        });

        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginPage.this.setVisible(false);
                new loginusers().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
