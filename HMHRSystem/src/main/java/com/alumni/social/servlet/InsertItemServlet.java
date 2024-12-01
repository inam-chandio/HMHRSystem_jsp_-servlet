package com.alumni.social.servlet;

import com.alumni.social.dao.ItemDao;
import com.alumni.social.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/InsertItemServlet")  // This is the URL pattern that matches your form's action
public class InsertItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Your logic to handle the form submission, e.g., add the item to the database
        String itemName = request.getParameter("item_name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));  // Get the quantity

        // Create a new Item object
        Item newItem = new Item();
        newItem.setItemName(itemName);
        newItem.setPrice(price);
        newItem.setQuantity(quantity);

        // Insert the item into the database using ItemDao
        ItemDao itemDao = new ItemDao();
        boolean isInserted = itemDao.addItem(newItem);

        // Redirect or forward based on success/failure
        if (isInserted) {
            response.sendRedirect("insertItem.jsp?success=true");  // Redirect with success message
        } else {
            response.sendRedirect("insertItem.jsp?error=true");  // Redirect with error message
        }
    }
}
