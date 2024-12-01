package com.alumni.social.servlet;

import com.alumni.social.model.CartItem;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ViewCartServlet")
public class ViewCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = 1; // Assume a logged-in user with ID = 1 for simplicity
        List<CartItem> cartItems = new ArrayList<>();

        try {
            // Get cart items from the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/alumnisocial", "root", "password");
            String sql = "SELECT * FROM Cart WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("item_name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                CartItem cartItem = new CartItem(name, price, quantity);
                cartItems.add(cartItem);
            }

            // Set the cart items in the request scope
            request.setAttribute("cartItems", cartItems);

            // Forward to the cart.jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
