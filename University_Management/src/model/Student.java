public class Student {
    private int studentId;
    private String name;
    private String gender;
    private String dateOfBirth;

    public Student(int studentId, String name, String gender, String dateOfBirth) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
