package com.example.servingwebcontent.model;

public class Subject {
    private String subjectId;
    private String subjectName;
    private int credits;
    private String lecturerId; // Thêm để khớp với bảng SQL

    public Subject() {
    }

    public Subject(String subjectId, String subjectName, int credits, String lecturerId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credits = credits;
        this.lecturerId = lecturerId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }
}
