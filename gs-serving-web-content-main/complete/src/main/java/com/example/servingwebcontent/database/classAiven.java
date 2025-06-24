package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.ClassRoom;

import java.sql.*;
import java.util.ArrayList;

public class classAiven {

    public void insertClass(ClassRoom c) {
        String sql = "INSERT INTO classroom (class_id, class_name) VALUES (?, ?)";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getClassId());
            pstmt.setString(2, c.getClassName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm lớp: " + e.getMessage(), e);
        }
    }

    public ArrayList<ClassRoom> getClassList() {
        ArrayList<ClassRoom> list = new ArrayList<>();
        String sql = "SELECT * FROM classroom";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ClassRoom c = new ClassRoom(
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
        String sql = "DELETE FROM classroom WHERE class_id = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, classId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xoá lớp: " + e.getMessage(), e);
        }
    }

    public void updateClass(ClassRoom c) {
        String sql = "UPDATE classroom SET class_name = ? WHERE class_id = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getClassName());
            pstmt.setString(2, c.getClassId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật lớp: " + e.getMessage(), e);
        }
    }

    public ClassRoom getClassById(String classId) {
        String sql = "SELECT * FROM classroom WHERE class_id = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, classId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ClassRoom(
                            rs.getString("class_id"),
                            rs.getString("class_name"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm lớp theo ID: " + e.getMessage(), e);
        }
        return null;
    }
}
