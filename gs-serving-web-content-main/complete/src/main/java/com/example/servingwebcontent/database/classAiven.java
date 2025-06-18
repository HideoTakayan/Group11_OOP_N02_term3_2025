package com.example.servingwebcontent.database;

import java.sql.*;
import java.util.ArrayList;

import com.example.servingwebcontent.model.ClassRoom;

public class classAiven {

    public void insertClass(ClassRoom c) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "INSERT INTO classroom (class_id, class_name) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, c.getClassId());
                pstmt.setString(2, c.getClassName());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error inserting class: " + e.getMessage());
        }
    }

    public ArrayList<ClassRoom> getClassList() {
        ArrayList<ClassRoom> list = new ArrayList<>();
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT * FROM classroom";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    ClassRoom c = new ClassRoom(
                            rs.getString("class_id"),
                            rs.getString("class_name"));
                    list.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving class list: " + e.getMessage());
        }
        return list;
    }

    public void deleteClass(String classId) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "DELETE FROM classroom WHERE class_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, classId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error deleting class: " + e.getMessage());
        }
    }

    public void updateClass(ClassRoom c) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "UPDATE classroom SET class_name = ? WHERE class_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, c.getClassName());
                pstmt.setString(2, c.getClassId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error updating class: " + e.getMessage());
        }
    }

    public ClassRoom getClassById(String classId) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT * FROM classroom WHERE class_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, classId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new ClassRoom(
                            rs.getString("class_id"),
                            rs.getString("class_name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting class: " + e.getMessage());
        }
        return null;
    }
}
