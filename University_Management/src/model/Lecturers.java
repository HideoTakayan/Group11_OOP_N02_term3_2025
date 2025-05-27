package University_Management.src.model;

// import University_Management.src.model.Person;

public class Lecturers implements Person {
    private int lecturerId;
    private String name;
    private String gender;
    private String dateOfBirth;

    public Lecturers(int lecturerId, String name, String gender, String dateOfBirth) {
        this.lecturerId = lecturerId;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return lecturerId;
    }

    public void setId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
