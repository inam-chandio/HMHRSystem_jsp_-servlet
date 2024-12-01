package com.alumni.social.servlet;

import com.alumni.social.db.DBConnection;
import com.alumni.social.model.Message;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/sendMessageServlet")  // URL pattern for the servlet
public class sendMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user inputs
        String messageContent = request.getParameter("message_content");
        int receiverId = Integer.parseInt(request.getParameter("receiver_id"));
        int senderId = (int) request.getSession().getAttribute("user_id"); // Assuming user_id is stored in session

        // Basic validation of the message (non-empty check)
        if (messageContent == null || messageContent.trim().isEmpty()) {
            response.sendRedirect("sendMessage.jsp?error=Message cannot be empty");
            return;
        }

        // Create a Message object
        Message message = new Message(senderId, receiverId, messageContent);

        // Insert the message into the database
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO Messages (sender_id, receiver_id, message, timestamp) VALUES (?, ?, ?, NOW())";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                // Set parameters for the SQL query
                ps.setInt(1, message.getSenderId());
                ps.setInt(2, message.getReceiverId());
                ps.setString(3, message.getMessageContent());

                // Execute the insert
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    // If the message was inserted successfully, redirect to the messages page or success page
                    response.sendRedirect("messages.jsp");
                } else {
                    // If insertion fails, redirect with an error message
                    response.sendRedirect("sendMessage.jsp?error=Failed to send message");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("sendMessage.jsp?error=Database error: " + e.getMessage());
        }
    }
}
