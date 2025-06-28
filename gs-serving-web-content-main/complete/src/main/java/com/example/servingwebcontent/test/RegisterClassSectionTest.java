package com.example.servingwebcontent.Test;

import com.example.servingwebcontent.model.RegisterClassSection;

public class RegisterClassSectionTest {
    public static void main(String[] args) {
        RegisterClassSection register = new RegisterClassSection(
                "DK001",
                "SV001",
                "LH001"
        );

        register.setName("Nguyễn Văn A");
        register.setClassName("Lập trình Java");

        System.out.println("=== RegisterClassSection Test ===");
        System.out.println("Mã đăng ký: " + register.getRegisterId());
        System.out.println("Mã sinh viên: " + register.getStudentId());
        System.out.println("Mã lớp học phần: " + register.getClassSectionId());
        System.out.println("Tên sinh viên: " + register.getName());
        System.out.println("Tên lớp học phần: " + register.getClassName());
    }
}
