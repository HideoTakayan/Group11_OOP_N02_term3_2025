package com.example.servingwebcontent.model;

public abstract class Person {
    protected String personId;
    protected String name;
    protected String address;
    protected String email;

    public Person() {
    }

    public Person(String personId, String name, String address, String email) {
        this.personId = personId;
        this.name = name;
        this.address = address;
        this.email = email;
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
}