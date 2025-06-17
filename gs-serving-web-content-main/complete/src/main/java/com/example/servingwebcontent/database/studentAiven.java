package com.example.servingwebcontent.database;

import java.sql.*;
import java.util.ArrayList;

import com.example.servingwebcontent.model.Student;

public class studentAiven {

    public void insertStudent(Student student) {
        try (Connection conn = aivenConnection.getConnection()) {
            String personSql = "INSERT INTO person (person_id, name, address, email) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(personSql)) {
                pstmt.setString(1, student.getPersonId());
                pstmt.setString(2, student.getName());
                pstmt.setString(3, student.getAddress());
                pstmt.setString(4, student.getEmail());
                pstmt.executeUpdate();
            }

            String studentSql = "INSERT INTO student (student_id, person_id, major, class_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(studentSql)) {
                pstmt.setString(1, student.getStudentId());
                pstmt.setString(2, student.getPersonId());
                pstmt.setString(3, student.getMajor());
                pstmt.setString(4, student.getClassId()); // dòng thêm vào
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }
    }

    public ArrayList<Student> getStudentList() {
        ArrayList<Student> students = new ArrayList<>();
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT s.student_id, p.person_id, p.name, p.address, p.email, s.major, s.class_id FROM student s JOIN person p ON s.person_id = p.person_id";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Student student = new Student(
                            rs.getString("student_id"),
                            rs.getString("person_id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getString("major"),
                            rs.getString("class_id"));
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }
        return students;
    }

    public void deleteStudent(String studentId) {
        try (Connection conn = aivenConnection.getConnection()) {
            String getPersonIdSql = "SELECT person_id FROM student WHERE student_id = ?";
            String personId = null;
            try (PreparedStatement pstmt = conn.prepareStatement(getPersonIdSql)) {
                pstmt.setString(1, studentId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    personId = rs.getString("person_id");
                }
            }

            String deleteStudentSql = "DELETE FROM student WHERE student_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteStudentSql)) {
                pstmt.setString(1, studentId);
                pstmt.executeUpdate();
            }

            if (personId != null) {
                String deletePersonSql = "DELETE FROM person WHERE person_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(deletePersonSql)) {
                    pstmt.setString(1, personId);
                    pstmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    public void updateStudent(Student s) {
        try (Connection conn = aivenConnection.getConnection()) {
            String updatePersonSql = "UPDATE person SET name = ?, address = ?, email = ? WHERE person_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updatePersonSql)) {
                pstmt.setString(1, s.getName());
                pstmt.setString(2, s.getAddress());
                pstmt.setString(3, s.getEmail());
                pstmt.setString(4, s.getPersonId());
                pstmt.executeUpdate();
            }

            String updateStudentSql = "UPDATE student SET major = ? WHERE student_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateStudentSql)) {
                pstmt.setString(1, s.getMajor());
                pstmt.setString(2, s.getStudentId());
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    public Student getStudentById(String studentId) {
        try (Connection conn = aivenConnection.getConnection()) {
            String sql = "SELECT s.student_id, p.person_id, p.name, p.address, p.email, s.major, s.class_id " +
                    "FROM student s JOIN person p ON s.person_id = p.person_id WHERE s.student_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, studentId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new Student(
                            rs.getString("student_id"),
                            rs.getString("person_id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getString("major"),
                            rs.getString("class_id"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving student by ID: " + e.getMessage());
        }
        return null;
    }
}
