package University_Management.src.model;

import java.time.LocalDate;

public class Lecturer {
    private int lecturerID;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;

    public Lecturer(int lecturerID, String name, LocalDate dateOfBirth, String gender) {
        this.lecturerID = lecturerID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    // Getters
    public int getLecturerID() {
        return lecturerID;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    // Setters
    public void setLecturerID(int lecturerID) {
        this.lecturerID = lecturerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // toString
    @Override
    public String toString() {
        return lecturerID + " - " + name;
    }
}
