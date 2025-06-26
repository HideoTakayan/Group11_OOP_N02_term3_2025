package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertToAiven {

    public void insertToAivenDb(User u) {
        // Chèn vào bảng `users` các cột email, password, role (user_id tự tăng)
        String sql = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, u.getEmail());
            pstmt.setString(2, u.getPassword());
            pstmt.setString(3, u.getRole());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chèn dữ liệu người dùng vào Aiven: " + e.getMessage(), e);
        }
    }
}
