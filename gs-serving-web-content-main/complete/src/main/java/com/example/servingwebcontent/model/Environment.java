package com.example.servingwebcontent.model;

public class Environment {
    private String enviromentId;
    private String classId;
    private String className;
    private String subjectName;
    private String lecturerName;
    private String location;
    private String dayOfWeek; // VD: "Thứ 2 - 2025-07-01"
    private String time;

    public Environment() {
    }

    public Environment(String enviromentId, String classId, String className, String subjectName,
                       String lecturerName, String location, String dayOfWeek, String time) {
        this.enviromentId = enviromentId;
        this.classId = classId;
        this.className = className;
        this.subjectName = subjectName;
        this.lecturerName = lecturerName;
        this.location = location;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
    }

    public String getEnviromentId() {
        return enviromentId;
    }

    public void setEnviromentId(String enviromentId) {
        this.enviromentId = enviromentId;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
