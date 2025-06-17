package com.example.servingwebcontent.database;

import java.sql.*;
import java.util.ArrayList;

import com.example.servingwebcontent.model.ExamSchedule;

public class examScheduleAiven {

    public void insertExamSchedule(ExamSchedule exam) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "INSERT INTO exam_schedule (subject, exam_date, exam_time, room, building) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, exam.getSubject());
                pstmt.setDate(2, exam.getExamDate());
                pstmt.setTime(3, exam.getExamTime());
                pstmt.setString(4, exam.getRoom());
                pstmt.setString(5, exam.getBuilding());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error inserting exam schedule: " + e.getMessage());
        }
    }

    public ArrayList<ExamSchedule> getExamSchedules() {
        ArrayList<ExamSchedule> exams = new ArrayList<>();
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT * FROM exam_schedule";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    ExamSchedule exam = new ExamSchedule(
                            rs.getString("subject"),
                            rs.getDate("exam_date"),
                            rs.getTime("exam_time"),
                            rs.getString("room"),
                            rs.getString("building"));
                    exams.add(exam);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting exams: " + e.getMessage());
        }
        return exams;
    }

    public void updateExamSchedule(String originalSubject, ExamSchedule exam) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "UPDATE exam_schedule SET subject = ?, exam_date = ?, exam_time = ?, room = ?, building = ? WHERE subject = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, exam.getSubject());
                pstmt.setDate(2, exam.getExamDate());
                pstmt.setTime(3, exam.getExamTime());
                pstmt.setString(4, exam.getRoom());
                pstmt.setString(5, exam.getBuilding());
                pstmt.setString(6, originalSubject);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error updating exam schedule: " + e.getMessage());
        }
    }

    public void deleteExamSchedule(String subject) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "DELETE FROM exam_schedule WHERE subject = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, subject);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error deleting exam schedule: " + e.getMessage());
        }
    }

    public ExamSchedule getExamBySubject(String subject) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT * FROM exam_schedule WHERE subject = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, subject);
                ResultSet rs = pstmt.executeQuery();
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
            System.out.println("Error getting exam by subject: " + e.getMessage());
        }
        return null;
    }
}
