package com.example.servingwebcontent.model;

public class ClassSection {
    private String classId;
    private String className;
    private String subjectId;
    private String lectureId;
    private String subjectName;
    private String lectureName;

    public String getSubjectName() {
        return subjectName;
    }

    public ClassSection(String classId, String className, String subjectId, String lectureId) {
        this.classId = classId;
        this.className = className;
        this.subjectId = subjectId;
        this.lectureId = lectureId;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
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

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }
}