package com.example.servingwebcontent.database;

import java.sql.*;
import java.util.*;
import com.example.servingwebcontent.model.RegisterClassSection;

public class registerClassAiven {

    public void insertRegisterClass(RegisterClassSection rc) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "INSERT INTO register_class (register_id, student_id, class_id) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                System.out.println(
                        "INSERT: " + rc.getRegisterId() + ", " + rc.getStudentId() + ", " + rc.getClassSectionId());
                stmt.setString(1, rc.getRegisterId());
                stmt.setString(2, rc.getStudentId());
                stmt.setString(3, rc.getClassSectionId());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi insert register_class:");
            e.printStackTrace();
        }
    }

    public List<RegisterClassSection> getRegisterClassList() {
        List<RegisterClassSection> list = new ArrayList<>();
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = """
                        SELECT r.register_id, r.student_id, p.name AS student_name,
                               r.class_id, c.class_name
                        FROM register_class r
                        JOIN student s ON r.student_id = s.student_id
                        JOIN person p ON s.person_id = p.person_id
                        JOIN class_section c ON r.class_id = c.class_id
                    """;
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                RegisterClassSection rc = new RegisterClassSection(
                        rs.getString("register_id"),
                        rs.getString("student_id"),
                        rs.getString("class_id"));
                rc.setName(rs.getString("student_name"));
                rc.setClassName(rs.getString("class_name"));
                list.add(rc);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách đăng ký lớp:");
            e.printStackTrace();
        }
        return list;
    }

    public RegisterClassSection getRegisterClassById(String registerId) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT * FROM register_class WHERE register_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, registerId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new RegisterClassSection(
                            rs.getString("register_id"),
                            rs.getString("student_id"),
                            rs.getString("class_id"));
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy đăng ký theo ID:");
            e.printStackTrace();
        }
        return null;
    }

    public void updateRegisterClass(RegisterClassSection rc) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "UPDATE register_class SET student_id = ?, class_id = ? WHERE register_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, rc.getStudentId());
                stmt.setString(2, rc.getClassSectionId());
                stmt.setString(3, rc.getRegisterId());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi update đăng ký lớp:");
            e.printStackTrace();
        }
    }

    public void deleteRegisterClass(String registerId) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "DELETE FROM register_class WHERE register_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, registerId);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa đăng ký lớp:");
            e.printStackTrace();
        }
    }
}
