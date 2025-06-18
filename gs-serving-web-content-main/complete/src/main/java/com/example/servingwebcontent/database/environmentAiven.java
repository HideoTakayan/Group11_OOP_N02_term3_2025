package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.servingwebcontent.model.Environment;

public class environmentAiven {

    public void insertEnvironment(Environment env) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "INSERT INTO environment (env_id, room_number, building, subject, lecture_id, student_count, day_of_week, time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, env.getEnvId());
                pstmt.setString(2, env.getRoomNumber());
                pstmt.setString(3, env.getBuilding());
                pstmt.setString(4, env.getSubject());
                pstmt.setString(5, env.getLectureId());
                pstmt.setInt(6, env.getStudentCount());
                pstmt.setString(7, env.getDayOfWeek());
                pstmt.setString(8, env.getTime());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error inserting environment: " + e.getMessage());
        }
    }

    public ArrayList<Environment> getEnvironments() {
        ArrayList<Environment> list = new ArrayList<>();
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT * FROM environment";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
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
            }
        } catch (SQLException e) {
            System.out.println("Error loading environments: " + e.getMessage());
        }
        return list;
    }

    public Environment getEnvironmentById(String envId) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT * FROM environment WHERE env_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, envId);
                ResultSet rs = pstmt.executeQuery();
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
            System.out.println("Error loading environment by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateEnvironment(Environment env) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "UPDATE environment SET room_number=?, building=?, subject=?, lecture_id=?, student_count=?, day_of_week=?, time=? WHERE env_id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, env.getRoomNumber());
                pstmt.setString(2, env.getBuilding());
                pstmt.setString(3, env.getSubject());
                pstmt.setString(4, env.getLectureId());
                pstmt.setInt(5, env.getStudentCount());
                pstmt.setString(6, env.getDayOfWeek());
                pstmt.setString(7, env.getTime());
                pstmt.setString(8, env.getEnvId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error updating environment: " + e.getMessage());
        }
    }

    public void deleteEnvironment(String envId) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "DELETE FROM environment WHERE env_id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, envId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error deleting environment: " + e.getMessage());
        }
    }
}
