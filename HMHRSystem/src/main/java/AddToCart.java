import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String itemName = request.getParameter("item_name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int itemId = Integer.parseInt(request.getParameter("item_id"));

        try (Connection conn = DBConnection.getConnection()) {
            // Check if the user_id exists in the Users table
            if (!userExists(conn, userId)) {
                response.sendRedirect("insertCart.jsp?error=true&message=User does not exist");
                return;
            }

            // Check if the item_id exists in the Items table
            if (!itemExists(conn, itemId)) {
                response.sendRedirect("insertCart.jsp?error=true&message=Item does not exist");
                return;
            }

            // Insert the cart item into the Cart table
            String query = "INSERT INTO Cart (user_id, item_name, price, quantity, item_id) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                stmt.setString(2, itemName);
                stmt.setDouble(3, price);
                stmt.setInt(4, quantity);
                stmt.setInt(5, itemId);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    response.sendRedirect("insertCart.jsp?success=true");
                } else {
                    response.sendRedirect("insertCart.jsp?error=true");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("insertCart.jsp?error=true");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("insertCart.jsp?error=true");
        }
    }

    private boolean userExists(Connection conn, int userId) throws SQLException {
        String query = "SELECT 1 FROM Users WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // If user exists, rs.next() returns true
            }
        }
    }

    private boolean itemExists(Connection conn, int itemId) throws SQLException {
        String query = "SELECT 1 FROM Items WHERE item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // If item exists, rs.next() returns true
            }
        }
    }
}
