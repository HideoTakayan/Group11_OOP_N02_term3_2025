package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.Environment;

import java.sql.*;
import java.util.ArrayList;

public class environmentAiven {

    public void insertEnvironment(Environment env) {
        String sql = """
            INSERT INTO enviroment (enviroment_id, class_id, class_name, subject_name,
                                    lecturer_name, location, day_of_week, time)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, env.getEnviromentId());
            pstmt.setString(2, env.getClassId());
            pstmt.setString(3, env.getClassName());
            pstmt.setString(4, env.getSubjectName());
            pstmt.setString(5, env.getLecturerName());
            pstmt.setString(6, env.getLocation());
            pstmt.setString(7, env.getDayOfWeek());
            pstmt.setString(8, env.getTime());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm môi trường lớp học: " + e.getMessage(), e);
        }
    }

    public ArrayList<Environment> getEnvironments() {
        ArrayList<Environment> list = new ArrayList<>();
        String sql = "SELECT * FROM enviroment";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Environment env = new Environment(
                        rs.getString("enviroment_id"),
                        rs.getString("class_id"),
                        rs.getString("class_name"),
                        rs.getString("subject_name"),
                        rs.getString("lecturer_name"),
                        rs.getString("location"),
                        rs.getString("day_of_week"),
                        rs.getString("time")
                );
                list.add(env);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách môi trường lớp học: " + e.getMessage(), e);
        }
        return list;
    }

    public Environment getEnvironmentById(String envId) {
        String sql = "SELECT * FROM enviroment WHERE enviroment_id = ?";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, envId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Environment(
                            rs.getString("enviroment_id"),
                            rs.getString("class_id"),
                            rs.getString("class_name"),
                            rs.getString("subject_name"),
                            rs.getString("lecturer_name"),
                            rs.getString("location"),
                            rs.getString("day_of_week"),
                            rs.getString("time")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm môi trường theo ID: " + e.getMessage(), e);
        }
        return null;
    }

    public void updateEnvironment(Environment env) {
        String sql = """
            UPDATE enviroment
            SET class_id = ?, class_name = ?, subject_name = ?, lecturer_name = ?,
                location = ?, day_of_week = ?, time = ?
            WHERE enviroment_id = ?
        """;

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, env.getClassId());
            pstmt.setString(2, env.getClassName());
            pstmt.setString(3, env.getSubjectName());
            pstmt.setString(4, env.getLecturerName());
            pstmt.setString(5, env.getLocation());
            pstmt.setString(6, env.getDayOfWeek());
            pstmt.setString(7, env.getTime());
            pstmt.setString(8, env.getEnviromentId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật môi trường lớp học: " + e.getMessage(), e);
        }
    }

    public void deleteEnvironment(String envId) {
        String sql = "DELETE FROM enviroment WHERE enviroment_id = ?";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, envId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xoá môi trường lớp học: " + e.getMessage(), e);
        }
    }
}
