package com.example.servingwebcontent.test;

import com.example.servingwebcontent.model.ClassSection;

public class ClassSectionTest {
    public static void main(String[] args) {
        ClassSection cs = new ClassSection(
                "C001",
                "Lập trình Java",
                "S001",
                "Java cơ bản",
                "L001",
                "Nguyễn Văn A");

        System.out.println("=== ClassSection Test ===");
        System.out.println("Mã lớp học phần: " + cs.getClassId());
        System.out.println("Tên lớp học phần: " + cs.getClassName());
        System.out.println("Mã môn học: " + cs.getSubjectId());
        System.out.println("Tên môn học: " + cs.getSubjectName());
        System.out.println("Mã giảng viên: " + cs.getLecturerId());
        System.out.println("Tên giảng viên: " + cs.getLecturerName());
    }
}
