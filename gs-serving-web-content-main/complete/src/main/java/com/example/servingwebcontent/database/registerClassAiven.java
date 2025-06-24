package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.RegisterClassSection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class registerClassAiven {

    public void insertRegisterClass(RegisterClassSection rc) {
        String sql = "INSERT INTO register_class (register_id, student_id, class_id) VALUES (?, ?, ?)";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rc.getRegisterId());
            stmt.setString(2, rc.getStudentId());
            stmt.setString(3, rc.getClassSectionId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm đăng ký lớp: " + e.getMessage(), e);
        }
    }

    public List<RegisterClassSection> getRegisterClassList() {
        List<RegisterClassSection> list = new ArrayList<>();
        String sql = """
            SELECT r.register_id, r.student_id, p.name AS student_name,
                   r.class_id, c.class_name
            FROM register_class r
            JOIN student s ON r.student_id = s.student_id
            JOIN person p ON s.person_id = p.person_id
            JOIN class_section c ON r.class_id = c.class_id
        """;

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RegisterClassSection rc = new RegisterClassSection(
                        rs.getString("register_id"),
                        rs.getString("student_id"),
                        rs.getString("class_id"));
                rc.setName(rs.getString("student_name"));
                rc.setClassName(rs.getString("class_name"));
                list.add(rc);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách đăng ký lớp: " + e.getMessage(), e);
        }

        return list;
    }

    public RegisterClassSection getRegisterClassById(String registerId) {
        String sql = "SELECT * FROM register_class WHERE register_id = ?";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, registerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RegisterClassSection(
                            rs.getString("register_id"),
                            rs.getString("student_id"),
                            rs.getString("class_id"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy đăng ký lớp theo ID: " + e.getMessage(), e);
        }

        return null;
    }

    public void updateRegisterClass(RegisterClassSection rc) {
        String sql = "UPDATE register_class SET student_id = ?, class_id = ? WHERE register_id = ?";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rc.getStudentId());
            stmt.setString(2, rc.getClassSectionId());
            stmt.setString(3, rc.getRegisterId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật đăng ký lớp: " + e.getMessage(), e);
        }
    }

    public void deleteRegisterClass(String registerId) {
        String sql = "DELETE FROM register_class WHERE register_id = ?";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, registerId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xoá đăng ký lớp: " + e.getMessage(), e);
        }
    }
}
