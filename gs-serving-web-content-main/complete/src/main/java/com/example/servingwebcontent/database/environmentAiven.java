package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.Environment;

import java.sql.*;
import java.util.ArrayList;

public class environmentAiven {

    public void insertEnvironment(Environment env) {
        String sql = "INSERT INTO environment (env_id, room_number, building, subject, lecture_id, student_count, day_of_week, time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = aivenConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, env.getEnvId());
            pstmt.setString(2, env.getRoomNumber());
            pstmt.setString(3, env.getBuilding());
            pstmt.setString(4, env.getSubject());
            pstmt.setString(5, env.getLectureId());
            pstmt.setInt(6, env.getStudentCount());
            pstmt.setString(7, env.getDayOfWeek());
            pstmt.setString(8, env.getTime());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm môi trường lớp học: " + e.getMessage(), e);
        }
    }

    public ArrayList<Environment> getEnvironments() {
        ArrayList<Environment> list = new ArrayList<>();
        String sql = "SELECT * FROM environment";

        try (Connection conn = aivenConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Environment env = new Environment(
                        rs.getString("env_id"),
                        rs.getString("room_number"),
                        rs.getString("building"),
                        rs.getString("subject"),
                        rs.getString("lecture_id"),
                        rs.getInt("student_count"),
                        rs.getString("day_of_week"),
                        rs.getString("time"));
                list.add(env);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách môi trường lớp học: " + e.getMessage(), e);
        }
        return list;
    }

    public Environment getEnvironmentById(String envId) {
        String sql = "SELECT * FROM environment WHERE env_id = ?";
        try (Connection conn = aivenConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, envId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Environment(
                            rs.getString("env_id"),
                            rs.getString("room_number"),
                            rs.getString("building"),
                            rs.getString("subject"),
                            rs.getString("lecture_id"),
                            rs.getInt("student_count"),
                            rs.getString("day_of_week"),
                            rs.getString("time"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm môi trường theo ID: " + e.getMessage(), e);
        }
        return null;
    }

    public void updateEnvironment(Environment env) {
        String sql = "UPDATE environment SET room_number = ?, building = ?, subject = ?, lecture_id = ?, student_count = ?, day_of_week = ?, time = ? WHERE env_id = ?";
        try (Connection conn = aivenConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, env.getRoomNumber());
            pstmt.setString(2, env.getBuilding());
            pstmt.setString(3, env.getSubject());
            pstmt.setString(4, env.getLectureId());
            pstmt.setInt(5, env.getStudentCount());
            pstmt.setString(6, env.getDayOfWeek());
            pstmt.setString(7, env.getTime());
            pstmt.setString(8, env.getEnvId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật môi trường lớp học: " + e.getMessage(), e);
        }
    }

    public void deleteEnvironment(String envId) {
        String sql = "DELETE FROM environment WHERE env_id = ?";
        try (Connection conn = aivenConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, envId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xoá môi trường lớp học: " + e.getMessage(), e);
        }
    }
}
