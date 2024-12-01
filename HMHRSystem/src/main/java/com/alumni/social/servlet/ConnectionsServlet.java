package com.alumni.social.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.alumni.social.db.DBConnection;

import java.io.IOException;
import java.sql.*;
import java.util.List;
@WebServlet("/ConnectionsServlet")
public class ConnectionsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");
        List<Connection> connections = ConnectionDAO.getConnectionsByUserId(userId);

        request.setAttribute("connections", connections);
        RequestDispatcher dispatcher = request.getRequestDispatcher("connections.jsp");
        dispatcher.forward(request, response);
    }

    // Logic for deleting connections or sending messages can be added in separate methods
}
