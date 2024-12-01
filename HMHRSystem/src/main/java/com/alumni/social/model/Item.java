package com.alumni.social.model;

public class Item {
    private int itemId;
    private String itemName; // This should match the field used in DB and DAO
    //private String description;
    private double price;
    private int quantity;

    // Constructor
    public Item(int itemId, String itemName, double price, int quantity) {
        this.itemId = itemId;
        this.itemName = itemName;
      //  this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Default Constructor
    public Item() {}

    // Getter and Setter Methods
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
/*
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }*/

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
