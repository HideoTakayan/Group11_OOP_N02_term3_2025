package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.Lecture;

import java.sql.*;
import java.util.ArrayList;

public class lectureAiven {

    public void insertLecture(Lecture lecture) {
        String personSql = "INSERT INTO person (person_id, name, address, email) VALUES (?, ?, ?, ?)";
        String lectureSql = "INSERT INTO lecture (lecture_id, person_id, department) VALUES (?, ?, ?)";

        try (Connection conn = aivenConnection.getConnection()) {

            try (PreparedStatement pstmt = conn.prepareStatement(personSql)) {
                pstmt.setString(1, lecture.getPersonId());
                pstmt.setString(2, lecture.getName());
                pstmt.setString(3, lecture.getAddress());
                pstmt.setString(4, lecture.getEmail());
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmt = conn.prepareStatement(lectureSql)) {
                pstmt.setString(1, lecture.getLectureId());
                pstmt.setString(2, lecture.getPersonId());
                pstmt.setString(3, lecture.getDepartment());
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm giảng viên: " + e.getMessage(), e);
        }
    }

    public ArrayList<Lecture> getLectureList() {
        ArrayList<Lecture> lectures = new ArrayList<>();
        String sql = """
                SELECT l.lecture_id, p.person_id, p.name, p.address, p.email, l.department
                FROM lecture l
                JOIN person p ON l.person_id = p.person_id
                """;

        try (Connection conn = aivenConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lectures.add(new Lecture(
                        rs.getString("lecture_id"),
                        rs.getString("person_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("department")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách giảng viên: " + e.getMessage(), e);
        }

        return lectures;
    }

    public Lecture getLectureById(String lectureId) {
        String sql = """
                SELECT l.lecture_id, p.person_id, p.name, p.address, p.email, l.department
                FROM lecture l
                JOIN person p ON l.person_id = p.person_id
                WHERE l.lecture_id = ?
                """;

        try (Connection conn = aivenConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lectureId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Lecture(
                            rs.getString("lecture_id"),
                            rs.getString("person_id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getString("department"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm giảng viên theo ID: " + e.getMessage(), e);
        }

        return null;
    }

    public void updateLecture(Lecture l) {
        String updatePerson = "UPDATE person SET name = ?, address = ?, email = ? WHERE person_id = ?";
        String updateLecture = "UPDATE lecture SET department = ? WHERE lecture_id = ?";

        try (Connection conn = aivenConnection.getConnection()) {

            try (PreparedStatement pstmt = conn.prepareStatement(updatePerson)) {
                pstmt.setString(1, l.getName());
                pstmt.setString(2, l.getAddress());
                pstmt.setString(3, l.getEmail());
                pstmt.setString(4, l.getPersonId());
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmt = conn.prepareStatement(updateLecture)) {
                pstmt.setString(1, l.getDepartment());
                pstmt.setString(2, l.getLectureId());
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật giảng viên: " + e.getMessage(), e);
        }
    }

    public void deleteLecture(String lectureId) {
        String getPersonIdSql = "SELECT person_id FROM lecture WHERE lecture_id = ?";
        String deleteLectureSql = "DELETE FROM lecture WHERE lecture_id = ?";
        String deletePersonSql = "DELETE FROM person WHERE person_id = ?";

        try (Connection conn = aivenConnection.getConnection()) {
            String personId = null;

            try (PreparedStatement pstmt = conn.prepareStatement(getPersonIdSql)) {
                pstmt.setString(1, lectureId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        personId = rs.getString("person_id");
                    }
                }
            }

            try (PreparedStatement pstmt = conn.prepareStatement(deleteLectureSql)) {
                pstmt.setString(1, lectureId);
                pstmt.executeUpdate();
            }

            if (personId != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(deletePersonSql)) {
                    pstmt.setString(1, personId);
                    pstmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xoá giảng viên: " + e.getMessage(), e);
        }
    }
}
