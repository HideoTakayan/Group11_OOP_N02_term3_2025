package University_Management.src.model;

public class Subject {
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
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
}
