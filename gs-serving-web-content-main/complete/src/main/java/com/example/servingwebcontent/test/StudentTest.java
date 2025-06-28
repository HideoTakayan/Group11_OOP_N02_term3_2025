package com.example.servingwebcontent.test;

import com.example.servingwebcontent.model.Student;

import java.sql.Date;

public class StudentTest {
    public static void main(String[] args) {
        Date dob = Date.valueOf("2004-09-10");

        Student student = new Student(
                "SV001",
                "P1001",
                "Lê Minh Tuấn",
                "789 Phạm Văn Đồng",
                "tuanlm@student.edu.vn",
                dob,
                "Nam",
                "CTK43A",
                "Công nghệ thông tin K43A");

        System.out.println("=== Student Test ===");
        System.out.println("Mã sinh viên: " + student.getStudentId());
        System.out.println("Mã người: " + student.getPersonId());
        System.out.println("Tên sinh viên: " + student.getName());
        System.out.println("Địa chỉ: " + student.getAddress());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Ngày sinh: " + student.getDateOfBirth());
        System.out.println("Giới tính: " + student.getGender());
        System.out.println("Mã lớp: " + student.getClassId());
        System.out.println("Tên lớp: " + student.getClassName());
    }
}
