package com.example.servingwebcontent.model;

public class Student extends Person {
    private String studentId;
    private String major;
    private String classId;

    public Student() {
    }

    public Student(String studentId, String personId, String name, String address, String email, String major,
            String classId) {
        super(personId, name, address, email);
        this.studentId = studentId;
        this.major = major;
        this.classId = classId;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}