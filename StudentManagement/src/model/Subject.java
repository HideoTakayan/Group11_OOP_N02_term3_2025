package StudentManagement.src.model;

public class Subject {
    private String id;
    private String name;
    private int credit;
    private Teacher teacher;

    public Subject(String id, String name, int credit, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getId() {
        return id;
    }

    public int getCredit() {
        return credit;
    }
}
