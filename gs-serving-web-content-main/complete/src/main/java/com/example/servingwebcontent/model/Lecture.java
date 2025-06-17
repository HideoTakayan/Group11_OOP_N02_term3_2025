package com.example.servingwebcontent.model;

public class Lecture extends Person {
    private String lectureId;
    private String department;

    public Lecture() {
    }

    public Lecture(String lectureId, String personId, String name, String address, String email, String department) {
        super(personId, name, address, email);
        this.lectureId = lectureId;
        this.department = department;
    }

    // Getters and Setters
    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}