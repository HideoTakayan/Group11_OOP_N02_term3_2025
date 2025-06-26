package com.example.servingwebcontent.database;

import java.sql.*;
import java.util.UUID;

public class personAiven {

    // ✅ TH1: Tạo person nếu chưa có, với person_id tự sinh
    public void insertPersonIfNotExists(String email) {
        String checkSql = "SELECT email FROM person WHERE email = ?";
        String insertSql = "INSERT INTO person (person_id, name, address, email, date_of_birth, gender) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, UUID.randomUUID().toString()); // person_id
                    insertStmt.setString(2, "Người dùng mới");             // name
                    insertStmt.setString(3, "Chưa cập nhật");              // address
                    insertStmt.setString(4, email);                        // email
                    insertStmt.setDate(5, Date.valueOf("2000-01-01"));     // birth
                    insertStmt.setString(6, "Nam");                        // gender
                    insertStmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chèn person nếu chưa tồn tại: " + e.getMessage(), e);
        }
    }

    // ✅ TH2: Tạo person với person_id cố định, nếu email chưa có
    public void insertPersonWithIdIfNotExists(String personId, String email) {
        String checkSql = "SELECT email FROM person WHERE email = ?";
        String insertSql = "INSERT INTO person (person_id, name, address, email, date_of_birth, gender) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, personId);                    // person_id cố định
                    insertStmt.setString(2, "Người dùng mới");           // name
                    insertStmt.setString(3, "Chưa cập nhật");            // address
                    insertStmt.setString(4, email);                      // email
                    insertStmt.setDate(5, Date.valueOf("2000-01-01"));   // birth
                    insertStmt.setString(6, "Nam");                      // gender
                    insertStmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chèn person (với ID): " + e.getMessage(), e);
        }
    }
}
