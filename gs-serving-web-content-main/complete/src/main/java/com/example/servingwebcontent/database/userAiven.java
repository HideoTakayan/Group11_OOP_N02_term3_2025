package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.User;
import java.sql.*;
import java.util.ArrayList;

public class userAiven {

    public void insertUser(User user) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "INSERT INTO users (user_name, address, email, password, role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User findByEmailAndPassword(String email, String password) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("id"));
                u.setUserName(rs.getString("user_name"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<User> userAivenList() {
        ArrayList<User> list = new ArrayList<>();
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT * FROM users";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("id"));
                u.setUserName(rs.getString("user_name"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteUserById(String id) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "UPDATE users SET user_name = ?, address = ?, email = ?, password = ?, role = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole());
            stmt.setInt(6, user.getUserID());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User findByEmail(String email) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("id"));
                u.setUserName(rs.getString("user_name"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(String id) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("id"));
                u.setUserName(rs.getString("user_name"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
