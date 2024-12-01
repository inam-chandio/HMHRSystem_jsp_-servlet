import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/InsertAndDisplayItems")
public class InsertAndDisplayItems extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Insert the item into the database
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO items (name, description, price, quantity) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, description);
                stmt.setDouble(3, price);
                stmt.setInt(4, quantity);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    // Item inserted successfully, redirect to the same page or another page
                    response.sendRedirect("insertAndDisplayItems.jsp?success=true");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("insertAndDisplayItems.jsp?error=true");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("insertAndDisplayItems.jsp?error=true");
        }
    }
}
