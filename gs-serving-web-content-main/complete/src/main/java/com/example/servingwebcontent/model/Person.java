package com.example.servingwebcontent.model;

import java.sql.Date;

public abstract class Person {
    protected String personId;
    protected String name;
    protected String address;
    protected String email;
    protected Date dateOfBirth;
    protected String gender; // "male" hoặc "female"

    public Person() {}

    public Person(String personId, String name, String address, String email, Date dateOfBirth, String gender) {
        this.personId = personId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    // Getters and Setters
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
