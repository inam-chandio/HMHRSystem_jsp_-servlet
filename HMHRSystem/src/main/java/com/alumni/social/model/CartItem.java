package com.alumni.social.model;

public class CartItem {
    private String name;
    private double price;
    private int quantity;

    // Constructor
    public CartItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Method to calculate total price (price * quantity)
    public double getTotal() {
        return price * quantity;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
