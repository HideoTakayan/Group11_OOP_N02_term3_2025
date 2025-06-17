package com.example.servingwebcontent.model;

public class RegisterClassSection {
    private String registerId;
    private String studentId;
    private String classSectionId;

    private String name;
    private String className;

    public RegisterClassSection(String registerId, String studentId, String classSectionId) {
        this.registerId = registerId;
        this.studentId = studentId;
        this.classSectionId = classSectionId;
    }

    public String getRegisterId() {
        return registerId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getClassSectionId() {
        return classSectionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }
}
