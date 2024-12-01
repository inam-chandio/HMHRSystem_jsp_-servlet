package com.alumni.social.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Database credentials
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/AlumniSocial?useSSL=false&serverTimezone=UTC";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "9787";

    // Load MySQL driver
    static {
        try {
            // Load and register the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL driver not found: " + e.getMessage());
            throw new RuntimeException("Failed to load MySQL driver.", e);
        }
    }

    // Method to get database connection
    public static Connection getConnection() throws SQLException {
        // Establish and return a connection to the database
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}
