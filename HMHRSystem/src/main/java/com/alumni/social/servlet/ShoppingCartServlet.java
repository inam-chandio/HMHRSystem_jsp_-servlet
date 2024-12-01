package com.alumni.social.servlet;

import com.alumni.social.dao.ItemDao;
import com.alumni.social.model.Item;
import com.alumni.social.model.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {

    // Handle GET requests to display the shopping cart
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch the shopping cart from the session
        HttpSession session = request.getSession();
        List<OrderItem> cartItems = (List<OrderItem>) session.getAttribute("cart");
        if (cartItems == null) {
            cartItems = new ArrayList<OrderItem>(); // If no cart exists, initialize a new one
        }

        // Set the cart items attribute for the JSP
        request.setAttribute("cartItems", cartItems);

        // Forward to the JSP to display the cart
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    // Handle POST requests to add an item to the shopping cart
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the item ID and quantity from the request
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Fetch the item from the database using ItemDao
        ItemDao itemDao = new ItemDao();
        Item item = itemDao.getItemById(itemId);

        if (item != null && item.getStock() >= quantity) {
            // Create an OrderItem to represent this item in the cart
            OrderItem orderItem = new OrderItem(itemId, quantity);

            // Fetch the shopping cart from the session
            HttpSession session = request.getSession();
            List<OrderItem> cartItems = (List<OrderItem>) session.getAttribute("cart");
            if (cartItems == null) {
                cartItems = new ArrayList<OrderItem>(); // If no cart exists, initialize a new one
            }

            // Check if the item is already in the cart
            boolean itemExistsInCart = false;
            for (OrderItem existingItem : cartItems) {
                if (existingItem.getItemId() == itemId) {
                    // If the item is already in the cart, update the quantity
                    existingItem.setQuantity(existingItem.getQuantity() + quantity);
                    itemExistsInCart = true;
                    break;
                }
            }

            // If the item wasn't in the cart, add it
            if (!itemExistsInCart) {
                cartItems.add(orderItem);
            }

            // Update the cart in the session
            session.setAttribute("cart", cartItems);

            // Redirect to the cart page to view the updated cart
            response.sendRedirect("ShoppingCartServlet");
        } else {
            // If the item is not available or stock is insufficient, redirect to the home page or show an error
            response.sendRedirect("index.jsp?error=Item not available or insufficient stock.");
        }
    }
}
