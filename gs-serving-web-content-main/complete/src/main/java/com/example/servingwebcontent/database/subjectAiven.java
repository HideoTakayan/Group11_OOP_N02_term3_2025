package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class subjectAiven {

    public void insertSubject(Subject subject) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "INSERT INTO subject (subject_id, subject_name, credits) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, subject.getSubjectId());
                pstmt.setString(2, subject.getSubjectName());
                pstmt.setInt(3, subject.getCredits());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error inserting subject: " + e.getMessage());
        }
    }

    public ArrayList<Subject> getSubjectList() {
        ArrayList<Subject> subjects = new ArrayList<>();
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT subject_id, subject_name, credits FROM subject";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    subjects.add(new Subject(
                            rs.getString("subject_id"),
                            rs.getString("subject_name"),
                            rs.getInt("credits")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving subjects: " + e.getMessage());
        }
        return subjects;
    }

    public Subject getSubjectById(String subjectId) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT subject_id, subject_name, credits FROM subject WHERE subject_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, subjectId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new Subject(
                            rs.getString("subject_id"),
                            rs.getString("subject_name"),
                            rs.getInt("credits"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving subject: " + e.getMessage());
        }
        return null;
    }

    public void updateSubject(Subject subject) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "UPDATE subject SET subject_name = ?, credits = ? WHERE subject_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, subject.getSubjectName());
                pstmt.setInt(2, subject.getCredits());
                pstmt.setString(3, subject.getSubjectId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error updating subject: " + e.getMessage());
        }
    }

    public void deleteSubject(String subjectId) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "DELETE FROM subject WHERE subject_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, subjectId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error deleting subject: " + e.getMessage());
        }
    }
}
