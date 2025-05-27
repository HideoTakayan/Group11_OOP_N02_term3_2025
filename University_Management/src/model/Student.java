package University_Management.src.model;

// import University_Management.src.model.Person;

public class Student implements Person {
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

    public int getId() {
        return studentId;
    }

    public void setName(String name) {
        this.name = name;
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
