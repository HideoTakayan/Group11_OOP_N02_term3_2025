package com.example.servingwebcontent.Test;

import com.example.servingwebcontent.model.Lecturer;

import java.sql.Date;

public class LecturerTest {
    public static void main(String[] args) {
        // Tạo ngày sinh: yyyy-mm-dd
        Date dateOfBirth = Date.valueOf("1980-07-15");

        // Tạo đối tượng Lecturer
        Lecturer lecturer = new Lecturer(
                "GV001",
                "P001",
                "Nguyễn Văn A",
                "123 Đường Lê Lợi",
                "nguyenvana@university.edu",
                dateOfBirth,
                "Nam",
                "Công nghệ thông tin"
        );

        // In thông tin
        System.out.println("=== Lecturer Test ===");
        System.out.println("Mã giảng viên: " + lecturer.getLecturerId());
        System.out.println("Mã người: " + lecturer.getPersonId());
        System.out.println("Tên giảng viên: " + lecturer.getName());
        System.out.println("Địa chỉ: " + lecturer.getAddress());
        System.out.println("Email: " + lecturer.getEmail());
        System.out.println("Ngày sinh: " + lecturer.getDateOfBirth());
        System.out.println("Giới tính: " + lecturer.getGender());
        System.out.println("Khoa/Phòng ban: " + lecturer.getDepartment());
    }
}
