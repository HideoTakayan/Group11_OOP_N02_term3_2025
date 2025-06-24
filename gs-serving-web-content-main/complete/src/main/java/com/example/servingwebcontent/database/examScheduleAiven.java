package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.ExamSchedule;

import java.sql.*;
import java.util.ArrayList;

public class examScheduleAiven {

    public void insertExamSchedule(ExamSchedule exam) {
        String sql = "INSERT INTO exam_schedule (subject, exam_date, exam_time, room, building) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, exam.getSubject());
            pstmt.setDate(2, exam.getExamDate());
            pstmt.setTime(3, exam.getExamTime());
            pstmt.setString(4, exam.getRoom());
            pstmt.setString(5, exam.getBuilding());
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
                        rs.getString("subject"),
                        rs.getDate("exam_date"),
                        rs.getTime("exam_time"),
                        rs.getString("room"),
                        rs.getString("building"));
                exams.add(exam);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách lịch thi: " + e.getMessage(), e);
        }
        return exams;
    }

    public void updateExamSchedule(String originalSubject, ExamSchedule exam) {
        String sql = "UPDATE exam_schedule SET subject = ?, exam_date = ?, exam_time = ?, room = ?, building = ? WHERE subject = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, exam.getSubject());
            pstmt.setDate(2, exam.getExamDate());
            pstmt.setTime(3, exam.getExamTime());
            pstmt.setString(4, exam.getRoom());
            pstmt.setString(5, exam.getBuilding());
            pstmt.setString(6, originalSubject);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật lịch thi: " + e.getMessage(), e);
        }
    }

    public void deleteExamSchedule(String subject) {
        String sql = "DELETE FROM exam_schedule WHERE subject = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subject);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xoá lịch thi: " + e.getMessage(), e);
        }
    }

    public ExamSchedule getExamBySubject(String subject) {
        String sql = "SELECT * FROM exam_schedule WHERE subject = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subject);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ExamSchedule(
                            rs.getString("subject"),
                            rs.getDate("exam_date"),
                            rs.getTime("exam_time"),
                            rs.getString("room"),
                            rs.getString("building"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm lịch thi theo môn: " + e.getMessage(), e);
        }
        return null;
    }
}
