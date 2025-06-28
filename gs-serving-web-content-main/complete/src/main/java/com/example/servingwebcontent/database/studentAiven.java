package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class studentAiven {

    // Hàm tạo student từ person_id đã có sẵn (dành cho bước đăng ký)
    public void insertStudentWithPersonId(String personId) {
        String insertSql = "INSERT INTO student (student_id, person_id, class_id, class_name) VALUES (?, ?, ?, ?)";

        try (Connection conn = aivenConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(insertSql)) {

            pstmt.setString(1, UUID.randomUUID().toString()); // student_id
            pstmt.setString(2, personId); // person_id
            pstmt.setNull(3, java.sql.Types.VARCHAR); // class_id => NULL (không có lớp)
            pstmt.setString(4, ""); // class_name => rỗng
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tạo student trống: " + e.getMessage(), e);
        }
    }

    // Hàm cũ insert đầy đủ person + student
    public void insertStudent(Student student) {
        String personSql = "INSERT INTO person (person_id, name, address, email, date_of_birth, gender) VALUES (?, ?, ?, ?, ?, ?)";
        String studentSql = "INSERT INTO student (student_id, person_id, class_id, class_name) VALUES (?, ?, ?, ?)";

        try (Connection conn = aivenConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtPerson = conn.prepareStatement(personSql)) {
                pstmtPerson.setString(1, student.getPersonId());
                pstmtPerson.setString(2, student.getName());
                pstmtPerson.setString(3, student.getAddress());
                pstmtPerson.setString(4, student.getEmail());
                pstmtPerson.setDate(5, student.getDateOfBirth());
                pstmtPerson.setString(6, student.getGender());
                pstmtPerson.executeUpdate();
            }

            try (PreparedStatement pstmtStudent = conn.prepareStatement(studentSql)) {
                pstmtStudent.setString(1, student.getStudentId());
                pstmtStudent.setString(2, student.getPersonId());
                pstmtStudent.setString(3, student.getClassId());
                pstmtStudent.setString(4, student.getClassName());
                pstmtStudent.executeUpdate();
            }

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm sinh viên: " + e.getMessage(), e);
        }
    }

    // Các hàm get/update/delete giữ nguyên
    public ArrayList<Student> getStudentList() {
        ArrayList<Student> students = new ArrayList<>();
        String sql = """
                    SELECT s.student_id, p.person_id, p.name, p.address, p.email, p.date_of_birth, p.gender, s.class_id, s.class_name
                    FROM student s
                    JOIN person p ON s.person_id = p.person_id
                """;

        try (Connection conn = aivenConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getString("student_id"),
                        rs.getString("person_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getDate("date_of_birth"),
                        rs.getString("gender"),
                        rs.getString("class_id"),
                        rs.getString("class_name")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách sinh viên: " + e.getMessage(), e);
        }

        return students;
    }

    public Student getStudentById(String studentId) {
        String sql = """
                    SELECT s.student_id, p.person_id, p.name, p.address, p.email, p.date_of_birth, p.gender, s.class_id, s.class_name
                    FROM student s
                    JOIN person p ON s.person_id = p.person_id
                    WHERE s.student_id = ?
                """;

        try (Connection conn = aivenConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getString("student_id"),
                            rs.getString("person_id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getDate("date_of_birth"),
                            rs.getString("gender"),
                            rs.getString("class_id"),
                            rs.getString("class_name"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy sinh viên theo ID: " + e.getMessage(), e);
        }

        return null;
    }

    public Student getStudentByEmail(String email) {
        String sql = """
                    SELECT s.student_id, p.person_id, p.name, p.address, p.email, p.date_of_birth, p.gender, s.class_id, s.class_name
                    FROM student s
                    JOIN person p ON s.person_id = p.person_id
                    WHERE p.email = ?
                """;

        try (Connection conn = aivenConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getString("student_id"),
                            rs.getString("person_id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getDate("date_of_birth"),
                            rs.getString("gender"),
                            rs.getString("class_id"),
                            rs.getString("class_name"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm sinh viên theo email: " + e.getMessage(), e);
        }

        return null;
    }

    public void updateStudent(Student s) {
        String updatePersonSql = "UPDATE person SET name = ?, address = ?, email = ?, date_of_birth = ?, gender = ? WHERE person_id = ?";
        String updateStudentSql = "UPDATE student SET class_id = ?, class_name = ? WHERE student_id = ?";

        try (Connection conn = aivenConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtPerson = conn.prepareStatement(updatePersonSql)) {
                pstmtPerson.setString(1, s.getName());
                pstmtPerson.setString(2, s.getAddress());
                pstmtPerson.setString(3, s.getEmail());
                pstmtPerson.setDate(4, s.getDateOfBirth());
                pstmtPerson.setString(5, s.getGender());
                pstmtPerson.setString(6, s.getPersonId());
                pstmtPerson.executeUpdate();
            }

            try (PreparedStatement pstmtStudent = conn.prepareStatement(updateStudentSql)) {
                pstmtStudent.setString(1, s.getClassId());
                pstmtStudent.setString(2, s.getClassName());
                pstmtStudent.setString(3, s.getStudentId());
                pstmtStudent.executeUpdate();
            }

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật sinh viên: " + e.getMessage(), e);
        }
    }

    public void deleteStudent(String studentId) {
        String getPersonIdSql = "SELECT person_id FROM student WHERE student_id = ?";
        String deleteStudentSql = "DELETE FROM student WHERE student_id = ?";
        String deletePersonSql = "DELETE FROM person WHERE person_id = ?";

        try (Connection conn = aivenConnection.getConnection()) {
            conn.setAutoCommit(false);
            String personId = null;

            try (PreparedStatement pstmt = conn.prepareStatement(getPersonIdSql)) {
                pstmt.setString(1, studentId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        personId = rs.getString("person_id");
                    }
                }
            }

            try (PreparedStatement pstmt = conn.prepareStatement(deleteStudentSql)) {
                pstmt.setString(1, studentId);
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
            throw new RuntimeException("Lỗi khi xoá sinh viên: " + e.getMessage(), e);
        }
    }
}
