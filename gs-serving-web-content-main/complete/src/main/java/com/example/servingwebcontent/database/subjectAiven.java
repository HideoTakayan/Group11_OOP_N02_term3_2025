package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.Subject;

import java.sql.*;
import java.util.ArrayList;

public class subjectAiven {

    public void insertSubject(Subject subject) {
        String sql = "INSERT INTO subject (subject_id, subject_name, credits, lecturer_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subject.getSubjectId());
            pstmt.setString(2, subject.getSubjectName());
            pstmt.setInt(3, subject.getCredits());
            pstmt.setString(4, subject.getLecturerId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm môn học: " + e.getMessage(), e);
        }
    }

    public ArrayList<Subject> getSubjectList() {
        ArrayList<Subject> subjects = new ArrayList<>();
        String sql = "SELECT subject_id, subject_name, credits, lecturer_id FROM subject";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getString("subject_id"),
                        rs.getString("subject_name"),
                        rs.getInt("credits"),
                        rs.getString("lecturer_id")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách môn học: " + e.getMessage(), e);
        }

        return subjects;
    }

    public Subject getSubjectById(String subjectId) {
        String sql = "SELECT subject_id, subject_name, credits, lecturer_id FROM subject WHERE subject_id = ?";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subjectId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Subject(
                            rs.getString("subject_id"),
                            rs.getString("subject_name"),
                            rs.getInt("credits"),
                            rs.getString("lecturer_id"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy môn học theo ID: " + e.getMessage(), e);
        }

        return null;
    }

    public void updateSubject(Subject subject) {
        String sql = "UPDATE subject SET subject_name = ?, credits = ?, lecturer_id = ? WHERE subject_id = ?";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subject.getSubjectName());
            pstmt.setInt(2, subject.getCredits());
            pstmt.setString(3, subject.getLecturerId());
            pstmt.setString(4, subject.getSubjectId());
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
public ArrayList<Subject> searchSubjects(String keyword) {
    ArrayList<Subject> subjects = new ArrayList<>();
    String sql = """
        SELECT s.subject_id, s.subject_name, s.credits, s.lecturer_id
        FROM subject s
        LEFT JOIN lecturer l ON s.lecturer_id = l.lecturer_id
        LEFT JOIN person p ON l.person_id = p.person_id
        WHERE LOWER(s.subject_name) LIKE ? 
           OR CAST(s.credits AS CHAR) LIKE ?
           OR LOWER(p.name) LIKE ?
    """;

    try (Connection conn = aivenConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        String lowerKeyword = "%" + keyword.toLowerCase() + "%";
        pstmt.setString(1, lowerKeyword);
        pstmt.setString(2, lowerKeyword); // credits as string
        pstmt.setString(3, lowerKeyword); // lecturer name

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                subjects.add(new Subject(
                    rs.getString("subject_id"),
                    rs.getString("subject_name"),
                    rs.getInt("credits"),
                    rs.getString("lecturer_id")
                ));
            }
        }

    } catch (SQLException e) {
        throw new RuntimeException("Lỗi khi tìm môn học: " + e.getMessage(), e);
    }

    return subjects;
}


}
