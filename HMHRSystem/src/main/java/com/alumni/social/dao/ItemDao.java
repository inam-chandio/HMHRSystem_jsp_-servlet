package com.alumni.social.dao;

import com.alumni.social.model.Item;
import com.alumni.social.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {

    // Method to search items by keyword in the item_name column
    public List<Item> searchItems(String keyword) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM Items WHERE item_name LIKE ?"; // Ensure this matches your DB column name

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");  // Search for any item names containing the keyword
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setItemId(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));  // Make sure this matches your DB column name
              //  item.setDescription(rs.getString("description")); // Ensure column exists in DB
                item.setPrice(rs.getDouble("price"));
                item.setQuantity(rs.getInt("quantity"));
                items.add(item);
            }

        } catch (SQLException e) {
            // Log the exception for debugging
            System.err.println("Error executing search query: " + e.getMessage());
            e.printStackTrace();
        }

        return items;  // Return the list of items (could be empty)
    }

    // Method to get an item by its ID
    public Item getItemById(int itemId) {
        Item item = null;
        String sql = "SELECT * FROM Items WHERE item_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, itemId);  // Set the item ID in the query
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = new Item();
                item.setItemId(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));  // Ensure this matches your DB column name
               // item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setQuantity(rs.getInt("quantity"));
            }

        } catch (SQLException e) {
            // Log the exception for debugging
            System.err.println("Error executing getItemById query: " + e.getMessage());
            e.printStackTrace();
        }

        return item;  // Return the item, or null if not found
    }

    // Method to update the stock for an item
    public boolean updateItemStock(int itemId, int newStock) {
        String sql = "UPDATE Items SET quantity = ? WHERE item_id = ?";
        boolean isUpdated = false;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, newStock);  // Set the new stock value
            ps.setInt(2, itemId);    // Set the item ID in the query
            int rowsAffected = ps.executeUpdate();  // Execute the update

            isUpdated = rowsAffected > 0;  // If at least one row is affected, the update was successful

        } catch (SQLException e) {
            // Log the exception for debugging
            System.err.println("Error executing updateItemStock query: " + e.getMessage());
            e.printStackTrace();
        }

        return isUpdated;  // Return whether the update was successful
    }

    // Method to add an item to the database
    public boolean addItem(Item item) {
        String sql = "INSERT INTO Items (item_name, price, quantity) VALUES (?, ?, ?)";
        boolean isAdded = false;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, item.getItemName());  // Using itemName here
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getQuantity());

            int rowsAffected = ps.executeUpdate();  // Execute the insert

            isAdded = rowsAffected > 0;  // If at least one row is affected, the insert was successful

        } catch (SQLException e) {
            // Log the exception for debugging
            System.err.println("Error executing addItem query: " + e.getMessage());
            e.printStackTrace();
        }

        return isAdded;  // Return whether the item was successfully added
    }

}
