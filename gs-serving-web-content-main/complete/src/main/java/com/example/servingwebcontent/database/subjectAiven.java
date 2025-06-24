package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.Subject;

import java.sql.*;
import java.util.ArrayList;

public class subjectAiven {

    public void insertSubject(Subject subject) {
        String sql = "INSERT INTO subject (subject_id, subject_name, credits) VALUES (?, ?, ?)";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subject.getSubjectId());
            pstmt.setString(2, subject.getSubjectName());
            pstmt.setInt(3, subject.getCredits());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm môn học: " + e.getMessage(), e);
        }
    }

    public ArrayList<Subject> getSubjectList() {
        ArrayList<Subject> subjects = new ArrayList<>();
        String sql = "SELECT subject_id, subject_name, credits FROM subject";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getString("subject_id"),
                        rs.getString("subject_name"),
                        rs.getInt("credits")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách môn học: " + e.getMessage(), e);
        }

        return subjects;
    }

    public Subject getSubjectById(String subjectId) {
        String sql = "SELECT subject_id, subject_name, credits FROM subject WHERE subject_id = ?";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subjectId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Subject(
                            rs.getString("subject_id"),
                            rs.getString("subject_name"),
                            rs.getInt("credits"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy môn học theo ID: " + e.getMessage(), e);
        }

        return null;
    }

    public void updateSubject(Subject subject) {
        String sql = "UPDATE subject SET subject_name = ?, credits = ? WHERE subject_id = ?";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subject.getSubjectName());
            pstmt.setInt(2, subject.getCredits());
            pstmt.setString(3, subject.getSubjectId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật môn học: " + e.getMessage(), e);
        }
    }

    public void deleteSubject(String subjectId) {
        String sql = "DELETE FROM subject WHERE subject_id = ?";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subjectId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa môn học: " + e.getMessage(), e);
        }
    }
}
