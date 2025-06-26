package com.example.servingwebcontent.model;

import java.sql.Date;

public class Student extends Person {
    private String studentId;
    private String classId;
    private String className;

    public Student() {
        super();
    }

    public Student(String studentId, String personId, String name, String address, String email, Date dateOfBirth, String gender, String classId, String className) {
        super(personId, name, address, email, dateOfBirth, gender);
        this.studentId = studentId;
        this.classId = classId;
        this.className = className;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
