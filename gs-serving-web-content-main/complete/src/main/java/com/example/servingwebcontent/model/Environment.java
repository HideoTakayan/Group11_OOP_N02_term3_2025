package com.example.servingwebcontent.model;

public class Environment {
    private String envId;
    private String roomNumber;
    private String building;
    private String subject;
    private String lectureId;
    private String lectureName;
    private int studentCount;
    private String dayOfWeek;
    private String time;

    public Environment() {
    }

    // Getters and Setters
    public String getEnvId() {
        return envId;
    }

    public Environment(String envId, String roomNumber, String building, String subject, String lectureId,
            int studentCount, String dayOfWeek, String time) {
        this.envId = envId;
        this.roomNumber = roomNumber;
        this.building = building;
        this.subject = subject;
        this.lectureId = lectureId;
        this.studentCount = studentCount;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
    }

    public void setEnvId(String envId) {
        this.envId = envId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}