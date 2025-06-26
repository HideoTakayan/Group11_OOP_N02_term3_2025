package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.ExamSchedule;

import java.sql.*;
import java.util.ArrayList;

public class examScheduleAiven {

    public void insertExamSchedule(ExamSchedule exam) {
        String sql = "INSERT INTO exam_schedule (subject_name, exam_date, start_time, duration_minutes, exam_format, location) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, exam.getSubjectName());
            pstmt.setDate(2, exam.getExamDate());
            pstmt.setTime(3, exam.getStartTime());  // Sửa thành getStartTime()
            pstmt.setInt(4, exam.getDurationMinutes());
            pstmt.setString(5, exam.getExamFormat());
            pstmt.setString(6, exam.getLocation());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm lịch thi: " + e.getMessage(), e);
        }
    }

    public ArrayList<ExamSchedule> getExamSchedules() {
        ArrayList<ExamSchedule> exams = new ArrayList<>();
        String sql = "SELECT * FROM exam_schedule";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ExamSchedule exam = new ExamSchedule(
                        rs.getString("subject_name"),
                        rs.getDate("exam_date"),
                        rs.getTime("start_time"),
                        rs.getInt("duration_minutes"),
                        rs.getString("exam_format"),
                        rs.getString("location")
                );
                exams.add(exam);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách lịch thi: " + e.getMessage(), e);
        }
        return exams;
    }

    public void updateExamSchedule(String originalSubjectName, ExamSchedule exam) {
        String sql = "UPDATE exam_schedule SET subject_name = ?, exam_date = ?, start_time = ?, duration_minutes = ?, exam_format = ?, location = ? WHERE subject_name = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, exam.getSubjectName());
            pstmt.setDate(2, exam.getExamDate());
            pstmt.setTime(3, exam.getStartTime());  // Sửa thành getStartTime()
            pstmt.setInt(4, exam.getDurationMinutes());
            pstmt.setString(5, exam.getExamFormat());
            pstmt.setString(6, exam.getLocation());
            pstmt.setString(7, originalSubjectName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật lịch thi: " + e.getMessage(), e);
        }
    }

    public void deleteExamSchedule(String subjectName) {
        String sql = "DELETE FROM exam_schedule WHERE subject_name = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subjectName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xoá lịch thi: " + e.getMessage(), e);
        }
    }

    public ExamSchedule getExamBySubject(String subjectName) {
        String sql = "SELECT * FROM exam_schedule WHERE subject_name = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subjectName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ExamSchedule(
                            rs.getString("subject_name"),
                            rs.getDate("exam_date"),
                            rs.getTime("start_time"),
                            rs.getInt("duration_minutes"),
                            rs.getString("exam_format"),
                            rs.getString("location")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm lịch thi theo môn: " + e.getMessage(), e);
        }
        return null;
    }
}
