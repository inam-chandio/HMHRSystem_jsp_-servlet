package com.alumni.social.servlet;

import com.alumni.social.model.CartItem;
import com.alumni.social.model.Item;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("item_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int userId = 1; // Assume a logged-in user with ID = 1 for simplicity

        try {
            // Get item details from database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/alumnisocial", "root", "password");
            String sql = "SELECT * FROM Items WHERE item_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");

                // Add item to cart in Cart table
                sql = "INSERT INTO Cart (user_id, item_name, price, quantity, item_id) VALUES (?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, userId);
                stmt.setString(2, name);
                stmt.setDouble(3, price);
                stmt.setInt(4, quantity);
                stmt.setInt(5, itemId);
                stmt.executeUpdate();
            }

            // Redirect to the cart page
            response.sendRedirect("ViewCartServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
