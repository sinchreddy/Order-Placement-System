package main.java.com.plantnursery.model;
public class Product {
    private String name;
    private double price;
    private int quantity;
    private String imagePath;

    public Product(String name) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImagePath() {
        return imagePath;
    }

}
