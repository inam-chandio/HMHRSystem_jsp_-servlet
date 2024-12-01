<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.alumni.social.model.User" %>
<%@ page import="com.alumni.social.dao.UserDao" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp");
    }
    UserDao userDao = new UserDao();
    User user = userDao.getUserByUsername(username);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile - AlumniSocial</title>
</head>
<body>
    <h1>User Profile</h1>
    <p>Username: <%= user.getUsername() %></p>
    <p>Balance: <%= user.getBalance() %></p>
    <p>Role: <%= user.getRole() %></p>
    <p>Privacy Settings: <%= user.getPrivacySettings() %></p>
    
    <form action="UpdateProfileServlet" method="post">
        <label for="balance">Update Balance:</label>
        <input type="number" id="balance" name="balance" value="<%= user.getBalance() %>" step="0.01" required><br><br>
        
        <label for="privacySettings">Update Privacy Settings:</label>
        <input type="text" id="privacySettings" name="privacySettings" value="<%= user.getPrivacySettings() %>" required><br><br>
        
        <input type="submit" value="Update Profile">
    </form>
    
    <p><a href="index.jsp">Back to Home</a></p>
</body>
</html>
