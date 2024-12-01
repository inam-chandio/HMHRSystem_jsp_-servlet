package com.alumni.social.dao;

import com.alumni.social.model.Connection;
import com.alumni.social.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDao {

    public boolean addConnection(int userId, int connectedUserId) {
        String sql = "INSERT INTO Connections (user_id, connected_user_id, connection_status) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, connectedUserId);
            ps.setString(3, "connected"); // Default status can be 'connected'
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteConnection(int userId, int connectedUserId) {
        String sql = "DELETE FROM Connections WHERE user_id = ? AND connected_user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, connectedUserId);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Connection> getConnections(int userId) {
        List<Connection> connections = new ArrayList<>();
        String sql = "SELECT * FROM Connections WHERE user_id = ? OR connected_user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Connection connection = new Connection();
                connection.setConnectionId(rs.getInt("connection_id"));
                connection.setUserId(rs.getInt("user_id"));
                connection.setConnectedUserId(rs.getInt("connected_user_id"));
                connection.setConnectionStatus(rs.getString("connection_status"));
                connections.add(connection);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connections;
    }
}
