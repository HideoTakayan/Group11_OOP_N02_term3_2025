package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.Lecturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class lecturerAiven {

    // ✅ Thêm giảng viên trống từ person_id (dùng sau khi đăng ký)
    public void insertLecturerWithPersonId(String personId) {
        String insertSql = "INSERT INTO lecturer (lecturer_id, person_id, department) VALUES (?, ?, ?)";

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {

            pstmt.setString(1, UUID.randomUUID().toString()); // lecturer_id random
            pstmt.setString(2, personId);                     // person_id có sẵn
            pstmt.setString(3, "");                           // department rỗng
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tạo giảng viên trống: " + e.getMessage(), e);
        }
    }

    // ✅ Hàm cũ insert đầy đủ person + lecturer
    public void insertLecturer(Lecturer lecturer) {
        String personSql = "INSERT INTO person (person_id, name, address, email, date_of_birth, gender) VALUES (?, ?, ?, ?, ?, ?)";
        String lecturerSql = "INSERT INTO lecturer (lecturer_id, person_id, department) VALUES (?, ?, ?)";

        try (Connection conn = aivenConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtPerson = conn.prepareStatement(personSql)) {
                pstmtPerson.setString(1, lecturer.getPersonId());
                pstmtPerson.setString(2, lecturer.getName());
                pstmtPerson.setString(3, lecturer.getAddress());
                pstmtPerson.setString(4, lecturer.getEmail());
                pstmtPerson.setDate(5, lecturer.getDateOfBirth());
                pstmtPerson.setString(6, lecturer.getGender());
                pstmtPerson.executeUpdate();
            }

            try (PreparedStatement pstmtLecturer = conn.prepareStatement(lecturerSql)) {
                pstmtLecturer.setString(1, lecturer.getLecturerId());
                pstmtLecturer.setString(2, lecturer.getPersonId());
                pstmtLecturer.setString(3, lecturer.getDepartment());
                pstmtLecturer.executeUpdate();
            }

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm giảng viên: " + e.getMessage(), e);
        }
    }

    public ArrayList<Lecturer> getLecturerList() {
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        String sql = """
                SELECT l.lecturer_id, p.person_id, p.name, p.address, p.email, p.date_of_birth, p.gender, l.department
                FROM lecturer l
                JOIN person p ON l.person_id = p.person_id
                """;

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lecturers.add(new Lecturer(
                        rs.getString("lecturer_id"),
                        rs.getString("person_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getDate("date_of_birth"),
                        rs.getString("gender"),
                        rs.getString("department")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách giảng viên: " + e.getMessage(), e);
        }

        return lecturers;
    }

    public Lecturer getLecturerById(String lecturerId) {
        String sql = """
                SELECT l.lecturer_id, p.person_id, p.name, p.address, p.email, p.date_of_birth, p.gender, l.department
                FROM lecturer l
                JOIN person p ON l.person_id = p.person_id
                WHERE l.lecturer_id = ?
                """;

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lecturerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Lecturer(
                            rs.getString("lecturer_id"),
                            rs.getString("person_id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getDate("date_of_birth"),
                            rs.getString("gender"),
                            rs.getString("department"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm giảng viên theo ID: " + e.getMessage(), e);
        }

        return null;
    }

    public Lecturer getLecturerByEmail(String email) {
        String sql = """
                SELECT l.lecturer_id, p.person_id, p.name, p.address, p.email, p.date_of_birth, p.gender, l.department
                FROM lecturer l
                JOIN person p ON l.person_id = p.person_id
                WHERE p.email = ?
                """;

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Lecturer(
                            rs.getString("lecturer_id"),
                            rs.getString("person_id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getDate("date_of_birth"),
                            rs.getString("gender"),
                            rs.getString("department"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm giảng viên theo email: " + e.getMessage(), e);
        }

        return null;
    }

    public void updateLecturer(Lecturer lecturer) {
        String updatePerson = "UPDATE person SET name = ?, address = ?, email = ?, date_of_birth = ?, gender = ? WHERE person_id = ?";
        String updateLecturer = "UPDATE lecturer SET department = ? WHERE lecturer_id = ?";

        try (Connection conn = aivenConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtPerson = conn.prepareStatement(updatePerson)) {
                pstmtPerson.setString(1, lecturer.getName());
                pstmtPerson.setString(2, lecturer.getAddress());
                pstmtPerson.setString(3, lecturer.getEmail());
                pstmtPerson.setDate(4, lecturer.getDateOfBirth());
                pstmtPerson.setString(5, lecturer.getGender());
                pstmtPerson.setString(6, lecturer.getPersonId());
                pstmtPerson.executeUpdate();
            }

            try (PreparedStatement pstmtLecturer = conn.prepareStatement(updateLecturer)) {
                pstmtLecturer.setString(1, lecturer.getDepartment());
                pstmtLecturer.setString(2, lecturer.getLecturerId());
                pstmtLecturer.executeUpdate();
            }

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật giảng viên: " + e.getMessage(), e);
        }
    }

    public void deleteLecturer(String lecturerId) {
        String getPersonIdSql = "SELECT person_id FROM lecturer WHERE lecturer_id = ?";
        String deleteLecturerSql = "DELETE FROM lecturer WHERE lecturer_id = ?";
        String deletePersonSql = "DELETE FROM person WHERE person_id = ?";

        try (Connection conn = aivenConnection.getConnection()) {
            conn.setAutoCommit(false);
            String personId = null;

            try (PreparedStatement pstmt = conn.prepareStatement(getPersonIdSql)) {
                pstmt.setString(1, lecturerId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        personId = rs.getString("person_id");
                    }
                }
            }

            try (PreparedStatement pstmt = conn.prepareStatement(deleteLecturerSql)) {
                pstmt.setString(1, lecturerId);
                pstmt.executeUpdate();
            }

            if (personId != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(deletePersonSql)) {
                    pstmt.setString(1, personId);
                    pstmt.executeUpdate();
                }
            }

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xoá giảng viên: " + e.getMessage(), e);
        }
    }
}
