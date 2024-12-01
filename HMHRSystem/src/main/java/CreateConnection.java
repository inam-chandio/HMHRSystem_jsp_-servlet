
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


@WebServlet("/CreateConnection")
public class CreateConnection extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user IDs for the connection
        int userId1 = Integer.parseInt(request.getParameter("user_id1"));
        int userId2 = Integer.parseInt(request.getParameter("user_id2"));
        
        // Assuming the connected status is true by default
        boolean connected = true;
        
        // Database connection
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBConnection.getConnection();
            
            String query = "INSERT INTO connections (user_id1, user_id2, connected) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId1);
            stmt.setInt(2, userId2);
            stmt.setBoolean(3, connected);
            
            int result = stmt.executeUpdate();
            
            if (result > 0) {
                response.getWriter().println("Connection created successfully!");
            } else {
                response.getWriter().println("Failed to create connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred while creating connection: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
