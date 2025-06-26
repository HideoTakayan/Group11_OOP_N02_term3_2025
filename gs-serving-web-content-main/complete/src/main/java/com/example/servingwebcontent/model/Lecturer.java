package com.example.servingwebcontent.model;

import java.sql.Date;

public class Lecturer extends Person {
    private String lecturerId;   // Mã giảng viên
    private String department;   // Khoa / phòng ban

    public Lecturer() {
        super();
    }

    public Lecturer(String lecturerId, String personId, String name, String address, String email, Date dateOfBirth, String gender,
                    String department) {
        super(personId, name, address, email, dateOfBirth, gender);
        this.lecturerId = lecturerId;
        this.department = department;
    }

    // Getters and Setters
    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
