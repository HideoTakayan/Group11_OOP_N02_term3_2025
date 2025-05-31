package University_Management.src.model;

import java.time.LocalDate;

public class Enrollment {
    private int id;
    private int studentId;
    private int subjectId;
    private LocalDate enrollmentDate;

    public Enrollment() {
    }

    public Enrollment(int id, int studentId, int subjectId, LocalDate enrollmentDate) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
