import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PlaceOrder")
public class PlaceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data from the request
        int userId = Integer.parseInt(request.getParameter("user_id"));
        double total = Double.parseDouble(request.getParameter("total"));
        String status = request.getParameter("status");

        // Insert the order into the database
        try (Connection conn = DBConnection.getConnection()) {
            // Prepare the SQL query to insert the order
            String query = "INSERT INTO Orders (user_id, total, status) VALUES (?, ?, ?)";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, userId);
                pst.setDouble(2, total);
                pst.setString(3, status);
                
                // Execute the update
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    response.getWriter().write("Order placed successfully.");
                } else {
                    response.getWriter().write("Failed to place the order.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error placing order: " + e.getMessage());
        }
    }
}
