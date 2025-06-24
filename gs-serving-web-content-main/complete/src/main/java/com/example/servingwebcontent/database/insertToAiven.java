package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class insertToAiven {

    public void insertToAivenDb(User u) {
        String sql = "INSERT INTO user (userId, username, address) VALUES (?, ?, ?)";

        // Sinh userId ngẫu nhiên (nếu cần đảm bảo không trùng, dùng UUID hoặc sequence trong DB)
        String userId = "u" + new Random().nextInt(1000000);

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, u.getUserName());
            pstmt.setString(3, u.getAddress());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chèn dữ liệu người dùng vào Aiven: " + e.getMessage(), e);
        }
    }
}
