package com.alumni.social.model;

public class User {
    private int userId;
    private String username;
    private String password;
    private String role;
    private double balance;
    private String privacySettings;

    // Constructor to initialize the User object
    public User(int userId, String username, String password, String role, double balance, String privacySettings) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = balance;
        this.privacySettings = privacySettings;
    }

    // Getters and Setters for the class properties

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPrivacySettings() {
        return privacySettings;
    }

    public void setPrivacySettings(String privacySettings) {
        this.privacySettings = privacySettings;
    }

    // To String method to print user information
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", balance=" + balance +
                ", privacySettings='" + privacySettings + '\'' +
                '}';
    }
}
