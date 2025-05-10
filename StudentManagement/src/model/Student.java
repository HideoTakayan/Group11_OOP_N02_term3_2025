package StudentManagement.src.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private String gender;
    private LocalDate dob;
    private List<Subject> subjects = new ArrayList<>();

    public Student(String id, String name, String gender, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public List<Subject> getSubjects() {
        return subjects;
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
