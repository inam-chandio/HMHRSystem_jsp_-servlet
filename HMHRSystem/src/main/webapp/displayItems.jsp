<%@ page import="java.util.*, java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Items List</title>
</head>
<body>
    <h1>All Items</h1>

    <table border="1">
        <thead>
            <tr>
                <th>Item ID</th>
                <th>Item Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Item> items = (List<Item>) request.getAttribute("items");  // Get the list of items from the request
                for (Item item : items) {
            %>
            <tr>
                <td><%= item.getItemId() %></td>
                <td><%= item.getName() %></td>
                <td><%= item.getDescription() %></td>
                <td><%= item.getPrice() %></td>
                <td><%= item.getQuantity() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    
</body>
</html>
