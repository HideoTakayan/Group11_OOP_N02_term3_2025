package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.servingwebcontent.model.ClassSection;

public class classSectionAiven {
    public void insertClassSection(ClassSection cs) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "INSERT INTO class_section (class_id, class_name, subject_id, lecture_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cs.getClassId());
                pstmt.setString(2, cs.getClassName());
                pstmt.setString(3, cs.getSubjectId());
                pstmt.setString(4, cs.getLectureId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error inserting class section: " + e.getMessage());
        }
    }

    public ArrayList<ClassSection> getAllClassSections() {
        ArrayList<ClassSection> list = new ArrayList<>();
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT * FROM class_section";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    list.add(new ClassSection(
                            rs.getString("class_id"),
                            rs.getString("class_name"),
                            rs.getString("subject_id"),
                            rs.getString("lecture_id")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    public void updateClassSection(ClassSection cs) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "UPDATE class_section SET class_name=?, subject_id=?, lecture_id=? WHERE class_id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cs.getClassName());
                pstmt.setString(2, cs.getSubjectId());
                pstmt.setString(3, cs.getLectureId());
                pstmt.setString(4, cs.getClassId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error updating class section: " + e.getMessage());
        }
    }

    public ClassSection getClassSectionById(String id) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT * FROM class_section WHERE class_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new ClassSection(
                            rs.getString("class_id"),
                            rs.getString("class_name"),
                            rs.getString("subject_id"),
                            rs.getString("lecture_id"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching class section: " + e.getMessage());
        }
        return null;
    }

    public void deleteClassSection(String id) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "DELETE FROM class_section WHERE class_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error deleting class section: " + e.getMessage());
        }
    }
}
