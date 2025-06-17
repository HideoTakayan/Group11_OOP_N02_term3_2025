package com.example.servingwebcontent.model;

import java.sql.Date;
import java.sql.Time;

public class ExamSchedule {
    private String subject; // Tên môn thi
    private Date examDate; // Ngày thi
    private Time examTime; // Giờ thi
    private String room; // Phòng thi
    private String building; // Tòa nhà

    public ExamSchedule() {
    }

    public ExamSchedule(String subject, Date examDate, Time examTime, String room, String building) {
        this.subject = subject;
        this.examDate = examDate;
        this.examTime = examTime;
        this.room = room;
        this.building = building;
    }

    // Getters and Setters
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Time getExamTime() {
        return examTime;
    }

    public void setExamTime(Time examTime) {
        this.examTime = examTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
