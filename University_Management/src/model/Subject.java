package University_Management.src.model;

public class Subject implements Person {
    private int subjectId;
    private String subjectName;
    private int credit;
    private int lecturerId;

    public Subject(int subjectId, String subjectName, int credit, int lecturerId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credit = credit;
        this.lecturerId = lecturerId;
    }

    @Override
    public int getId() {
        return subjectId;
    }

    public void setId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getName() {
        return subjectName;
    }

    public void setName(String name) {
        this.subjectName = name;
    }
}
