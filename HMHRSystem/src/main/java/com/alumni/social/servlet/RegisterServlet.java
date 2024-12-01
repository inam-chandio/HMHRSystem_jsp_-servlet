package com.alumni.social.servlet;

import com.alumni.social.db.DBConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/RegisterServlet")  // URL pattern for the servlet
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user inputs
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // Plain text password
        String balanceStr = request.getParameter("balance");
        String role = request.getParameter("role"); // User's role (default 'user')
        String privacySettings = request.getParameter("privacy_settings"); // User's privacy setting

        // Validate balance input (should be a valid decimal)
        double balance = 0.0;
        try {
            balance = Double.parseDouble(balanceStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("register.jsp?error=Invalid balance input");
            return;
        }

        // Default role (if not provided in form)
        if (role == null) {
            role = "user";
        }

        // Default privacy settings (if not provided in form)
        if (privacySettings == null) {
            privacySettings = "public";
        }

        try (Connection conn = DBConnection.getConnection()) {
            // Prepare the SQL query to insert the user into the database
            String query = "INSERT INTO Users (username, password, role, balance, privacy_settings) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password); // Plain text password (consider hashing for production)
            ps.setString(3, role);
            ps.setDouble(4, balance);
            ps.setString(5, privacySettings);

            // Execute the query
            ps.executeUpdate();
            response.sendRedirect("login.jsp"); // Redirect to login page after successful registration
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=Database error"); // Redirect with error message
        }
    }
}
