package com.example.servingwebcontent.model;

public class User {
    private int userID; // Kiểu int để phù hợp với DB
    private String userName;
    private String email;
    private String password;
    private String address;
    private String role;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void printUserName(User u) {
        System.out.println("Submitted Name:");
        System.out.println(u.userName);
    }
}
