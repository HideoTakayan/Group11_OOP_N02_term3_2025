package com.example.servingwebcontent.database;

import java.sql.*;
import java.util.UUID;

public class personAiven {

    public void insertPersonIfNotExists(String email) {
        String checkSql = "SELECT email FROM person WHERE email = ?";
        String insertSql = "INSERT INTO person (person_id, name, address, email, date_of_birth, gender) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, UUID.randomUUID().toString());          // person_id
                    insertStmt.setString(2, "Người dùng mới");                     // name (default)
                    insertStmt.setString(3, "Chưa cập nhật");                      // address (default)
                    insertStmt.setString(4, email);                                // email
                    insertStmt.setDate(5, Date.valueOf("2000-01-01"));             // date_of_birth (default)
                    insertStmt.setString(6, "Nam");                                // gender (default: Nam/Nữ)

                    insertStmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chèn person nếu chưa tồn tại: " + e.getMessage(), e);
        }
    }
}
