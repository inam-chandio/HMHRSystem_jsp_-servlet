package com.alumni.social.model;

public class Connection {
    private int connectionId;
    private int userId;
    private int connectedUserId;
    private String connectionStatus; // e.g., active, blocked

    // Constructors, getters, and setters
    public Connection(int connectionId, int userId, int connectedUserId, String connectionStatus) {
        this.connectionId = connectionId;
        this.userId = userId;
        this.connectedUserId = connectedUserId;
        this.connectionStatus = connectionStatus;
    }

    // Getters and Setters
    public int getConnectionId() { return connectionId; }
    public void setConnectionId(int connectionId) { this.connectionId = connectionId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getConnectedUserId() { return connectedUserId; }
    public void setConnectedUserId(int connectedUserId) { this.connectedUserId = connectedUserId; }
    public String getConnectionStatus() { return connectionStatus; }
    public void setConnectionStatus(String connectionStatus) { this.connectionStatus = connectionStatus; }
}
