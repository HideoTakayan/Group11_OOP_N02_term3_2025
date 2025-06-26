package com.example.servingwebcontent.model;

public class ClassSection {
    private String classId;
    private String className;
    private String subjectId;
    private String subjectName;
    private String lecturerId;
    private String lecturerName;

    public ClassSection() {
    }

    public ClassSection(String classId, String className, String subjectId, String subjectName,
                        String lecturerId, String lecturerName) {
        this.classId = classId;
        this.className = className;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
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

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }
}
