package com.example.servingwebcontent.database;

import com.example.servingwebcontent.model.ClassSection;

import java.sql.*;
import java.util.ArrayList;

public class classSectionAiven {

    public void insertClassSection(ClassSection cs) {
        String sql = "INSERT INTO class_section (class_id, class_name, subject_id, lecturer_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cs.getClassId());
            pstmt.setString(2, cs.getClassName());
            pstmt.setString(3, cs.getSubjectId());
            pstmt.setString(4, cs.getLecturerId()); // ⚠ phải sửa lại thành lecturerId
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm lớp học phần: " + e.getMessage(), e);
        }
    }

    public ArrayList<ClassSection> getAllClassSections() {
        ArrayList<ClassSection> list = new ArrayList<>();
        String sql = """
            SELECT cs.class_id, cs.class_name, cs.subject_id, s.subject_name,
                   cs.lecturer_id, p.name AS lecturer_name
            FROM class_section cs
            JOIN subject s ON cs.subject_id = s.subject_id
            JOIN lecturer l ON cs.lecturer_id = l.lecturer_id
            JOIN person p ON l.person_id = p.person_id
            """;

        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new ClassSection(
                        rs.getString("class_id"),
                        rs.getString("class_name"),
                        rs.getString("subject_id"),
                        rs.getString("subject_name"),
                        rs.getString("lecturer_id"),
                        rs.getString("lecturer_name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách lớp học phần: " + e.getMessage(), e);
        }
        return list;
    }

    public void updateClassSection(ClassSection cs) {
        String sql = "UPDATE class_section SET class_name = ?, subject_id = ?, lecturer_id = ? WHERE class_id = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cs.getClassName());
            pstmt.setString(2, cs.getSubjectId());
            pstmt.setString(3, cs.getLecturerId());
            pstmt.setString(4, cs.getClassId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật lớp học phần: " + e.getMessage(), e);
        }
    }

    public ClassSection getClassSectionById(String id) {
        String sql = """
            SELECT cs.class_id, cs.class_name, cs.subject_id, s.subject_name,
                   cs.lecturer_id, p.name AS lecturer_name
            FROM class_section cs
            JOIN subject s ON cs.subject_id = s.subject_id
            JOIN lecturer l ON cs.lecturer_id = l.lecturer_id
            JOIN person p ON l.person_id = p.person_id
            WHERE cs.class_id = ?
            """;
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ClassSection(
                            rs.getString("class_id"),
                            rs.getString("class_name"),
                            rs.getString("subject_id"),
                            rs.getString("subject_name"),
                            rs.getString("lecturer_id"),
                            rs.getString("lecturer_name")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm lớp học phần theo ID: " + e.getMessage(), e);
        }
        return null;
    }

    public void deleteClassSection(String id) {
        String sql = "DELETE FROM class_section WHERE class_id = ?";
        try (Connection conn = aivenConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xoá lớp học phần: " + e.getMessage(), e);
        }
    }
    public ArrayList<ClassSection> searchClassSections(String keyword) {
    ArrayList<ClassSection> list = new ArrayList<>();
    String sql = """
        SELECT cs.class_id, cs.class_name, cs.subject_id, s.subject_name,
               cs.lecturer_id, p.name AS lecturer_name
        FROM class_section cs
        JOIN subject s ON cs.subject_id = s.subject_id
        JOIN lecturer l ON cs.lecturer_id = l.lecturer_id
        JOIN person p ON l.person_id = p.person_id
        WHERE LOWER(cs.class_name) LIKE ? OR LOWER(s.subject_name) LIKE ?
    """;

    try (Connection conn = aivenConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        String pattern = "%" + keyword.toLowerCase() + "%";
        pstmt.setString(1, pattern);
        pstmt.setString(2, pattern);

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ClassSection(
                        rs.getString("class_id"),
                        rs.getString("class_name"),
                        rs.getString("subject_id"),
                        rs.getString("subject_name"),
                        rs.getString("lecturer_id"),
                        rs.getString("lecturer_name")
                ));
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Lỗi khi tìm kiếm lớp học phần: " + e.getMessage(), e);
    }

    return list;
}

}
