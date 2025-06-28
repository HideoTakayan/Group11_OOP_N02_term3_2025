package com.example.servingwebcontent.test;

import com.example.servingwebcontent.model.Subject;

public class Subjecttest {
    public static void main(String[] args) {
        Subject subject = new Subject(
                "MH001",
                "Cấu trúc dữ liệu",
                3,
                "GV001");

        System.out.println("=== Subject Test ===");
        System.out.println("Mã môn học: " + subject.getSubjectId());
        System.out.println("Tên môn học: " + subject.getSubjectName());
        System.out.println("Số tín chỉ: " + subject.getCredits());
        System.out.println("Mã giảng viên phụ trách: " + subject.getLecturerId());
    }
}
