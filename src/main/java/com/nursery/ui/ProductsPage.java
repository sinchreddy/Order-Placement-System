package main.java.com.nursery.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ProductsPage extends JFrame {

    public ProductsPage() {
        setTitle("Nandishwara Nursery");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        JPanel bannerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel bannerLabel = new JLabel("<html><h1>NANDISHWARA NURSERY</h1></html>");
        bannerPanel.add(bannerLabel);

        JPanel productPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        productPanel.add(createProductCard("Brinjal", "brinjal.jpeg", "Rs. 60/tray", "A popular vegetable in Indian cuisine"));
        productPanel.add(createProductCard("Chilli", "chilli.jpeg", "Rs. 100/tray", "Adds spice to your dishes"));
        productPanel.add(createProductCard("Marigold", "marigold.jpeg", "Rs. 250/tray", "A beautiful flower for your garden"));
        productPanel.add(createProductCard("Cucumber", "cucumber.jpeg", "Rs. 100/tray", "A refreshing vegetable for salads"));
        productPanel.add(createProductCard("Tomato", "tomato.jpeg", "Rs. 160/tray", "A juicy fruit for your kitchen"));
        productPanel.add(createProductCard("Capsicum", "capsicum.jpeg", "Rs. 200/tray", "Adds flavor to your dishes"));
        productPanel.add(createProductCard("Cabbage", "cabbage.jpg", "Rs. 80/tray", "A healthy vegetable for your diet"));
        productPanel.add(createProductCard("Cauliflower", "cauli.jpg", "Rs. 80/tray", "A nutritious vegetable for your meals"));

        JButton addToCartButton = new JButton("ADD TO CART");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Going to cart!");
                OrderPage orderPage= new OrderPage();
                orderPage.setVisible(true);
                ProductsPage.this.setVisible(false);
            }
        });

        add(bannerPanel, BorderLayout.NORTH);
        add(productPanel, BorderLayout.CENTER);
        add(addToCartButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createProductCard(String productName, String imageName, String price, String description) {
        JPanel cardPanel = new JPanel(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/" + imageName)));
        Image image = imageIcon.getImage();
        int imageSize = 100;
        Image scaledImage = image.getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        cardPanel.add(imageLabel, BorderLayout.CENTER);

        JLabel productNameLabel = new JLabel("<html><h2>" + productName + "</h2></html>");
        cardPanel.add(productNameLabel, BorderLayout.NORTH);

        JLabel productDetailsLabel = new JLabel("<html><p>Price: " + price + "<br>Description: " + description + "</p></html>");
        cardPanel.add(productDetailsLabel, BorderLayout.SOUTH);

        return cardPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProductsPage();
            }
        });
    }
}
