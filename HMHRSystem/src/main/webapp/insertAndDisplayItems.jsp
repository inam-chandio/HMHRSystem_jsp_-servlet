<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Item</title>
</head>
<body>
    <h1>Insert Item</h1>

    <% 
        // Display success or error message based on the URL parameters
        String success = request.getParameter("success");
        String error = request.getParameter("error");
        if (success != null && success.equals("true")) {
    %>
        <p style="color: green;">Item inserted successfully!</p>
    <% 
        } else if (error != null && error.equals("true")) {
    %>
        <p style="color: red;">An error occurred while inserting the item. Please try again.</p>
    <% 
        }
    %>

    <form action="InsertAndDisplayItems" method="post">
        <label for="name">Item Name:</label>
        <input type="text" id="name" name="name" required><br><br>

        <label for="description">Description:</label>
        <textarea id="description" name="description"></textarea><br><br>

        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price" required><br><br>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required><br><br>

        <input type="submit" value="Insert Item">
    </form>
</body>
</html>
