package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.User;
import java.sql.*;
import java.util.ArrayList;

public class userAiven {

    public void insertUser(User user) {
        String sql = "INSERT INTO users (user_name, address, email, password, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm người dùng: " + e.getMessage(), e);
        }
    }

    public User findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapUser(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm người dùng theo email và mật khẩu", e);
        }
        return null;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapUser(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách người dùng", e);
        }
        return list;
    }

    public void deleteUserById(String id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa người dùng", e);
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET user_name = ?, address = ?, email = ?, password = ?, role = ? WHERE id = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole());
            stmt.setInt(6, user.getUserID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật người dùng", e);
        }
    }

    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapUser(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm người dùng theo email", e);
        }
        return null;
    }

    public User getUserById(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapUser(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm người dùng theo ID", e);
        }
        return null;
    }

    // 👇 Tách riêng phần map User để tái sử dụng
    private User mapUser(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUserID(rs.getInt("id"));
        u.setUserName(rs.getString("user_name"));
        u.setAddress(rs.getString("address"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        u.setRole(rs.getString("role"));
        return u;
    }
}
