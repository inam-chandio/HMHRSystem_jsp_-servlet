<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add to Cart</title>
</head>
<body>
    <h1>Add Item to Cart</h1>

    <!-- Display Success/Error Message -->
    <% 
        String success = request.getParameter("success");
        String error = request.getParameter("error");
        if (success != null && success.equals("true")) {
    %>
        <p style="color: green;">Item added to cart successfully!</p>
    <% 
        } else if (error != null && error.equals("true")) {
    %>
        <p style="color: red;">An error occurred while adding the item to the cart. Please try again.</p>
    <% 
        }
    %>

    <form action="AddToCart" method="post">
        <label for="user_id">User ID:</label>
        <input type="text" id="user_id" name="user_id" required><br><br>

        <label for="item_name">Item Name:</label>
        <input type="text" id="item_name" name="item_name" required><br><br>

        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price" required><br><br>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required><br><br>

        <label for="item_id">Item ID:</label>
        <input type="text" id="item_id" name="item_id" required><br><br>

        <input type="submit" value="Add to Cart">
    </form>
</body>
</html>
