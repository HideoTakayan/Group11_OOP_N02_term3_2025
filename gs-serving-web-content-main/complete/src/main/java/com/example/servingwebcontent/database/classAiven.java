package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.StudentClass;

import java.sql.*;
import java.util.ArrayList;

public class classAiven {

    public void insertClass(StudentClass c) {
        String sql = "INSERT INTO student_class (class_id, class_name) VALUES (?, ?)";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getClassId());
            pstmt.setString(2, c.getClassName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm lớp: " + e.getMessage(), e);
        }
    }

    public ArrayList<StudentClass> getClassList() {
        ArrayList<StudentClass> list = new ArrayList<>();
        String sql = "SELECT * FROM student_class";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                StudentClass c = new StudentClass(
                        rs.getString("class_id"),
                        rs.getString("class_name"));
                list.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách lớp: " + e.getMessage(), e);
        }
        return list;
    }

    public void deleteClass(String classId) {
        String sql = "DELETE FROM student_class WHERE class_id = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, classId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xoá lớp: " + e.getMessage(), e);
        }
    }

    public void updateClass(StudentClass c) {
        String sql = "UPDATE student_class SET class_name = ? WHERE class_id = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getClassName());
            pstmt.setString(2, c.getClassId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật lớp: " + e.getMessage(), e);
        }
    }

    public StudentClass getClassById(String classId) {
        String sql = "SELECT * FROM student_class WHERE class_id = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, classId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new StudentClass(
                            rs.getString("class_id"),
                            rs.getString("class_name"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm lớp theo ID: " + e.getMessage(), e);
        }
        return null;
    }
    public ArrayList<StudentClass> searchClassesByName(String keyword) {
    ArrayList<StudentClass> list = new ArrayList<>();
    String sql = "SELECT * FROM student_class WHERE LOWER(class_name) LIKE ?";
    try (Connection conn = aivenConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, "%" + keyword.toLowerCase() + "%");
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new StudentClass(
                        rs.getString("class_id"),
                        rs.getString("class_name")
                ));
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Lỗi khi tìm lớp học theo tên: " + e.getMessage(), e);
    }
    return list;
}

}
