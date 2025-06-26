package com.example.servingwebcontent.model;

public class User {
    private String userID; // Thay int thành String vì user_id trong DB là varchar(50)
    private String email;
    private String password;
    private String role;

    public User() {
    }

    public User(String userID, String email, String password, String role) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters và Setters
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    // Debug method
    public void printInfo() {
        System.out.println("User Info:");
        System.out.println("UserID: " + userID);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
    }
}
