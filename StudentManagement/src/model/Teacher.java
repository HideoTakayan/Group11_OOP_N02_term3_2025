package StudentManagement.src.model;

import java.time.LocalDate;

public class Teacher {
    private String id;
    private String name;
    private String gender;
    private LocalDate dob;

    public Teacher(String id, String name, String gender, LocalDate dob) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
    }
}
