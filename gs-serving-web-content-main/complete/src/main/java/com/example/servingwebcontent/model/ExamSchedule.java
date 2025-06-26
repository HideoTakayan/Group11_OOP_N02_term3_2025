package com.example.servingwebcontent.model;

import java.sql.Date;
import java.sql.Time;

public class ExamSchedule {
    private String subjectName;     // Tên môn thi
    private Date examDate;          // Ngày thi
    private Time startTime;         // Giờ bắt đầu thi
    private int durationMinutes;    // Thời lượng thi (phút)
    private String examFormat;      // Hình thức thi
    private String location;        // Địa điểm thi

    public ExamSchedule() {
    }

    public ExamSchedule(String subjectName, Date examDate, Time startTime, int durationMinutes, String examFormat, String location) {
        this.subjectName = subjectName;
        this.examDate = examDate;
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
        this.examFormat = examFormat;
        this.location = location;
    }

    // Getters and Setters
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getExamFormat() {
        return examFormat;
    }

    public void setExamFormat(String examFormat) {
        this.examFormat = examFormat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
