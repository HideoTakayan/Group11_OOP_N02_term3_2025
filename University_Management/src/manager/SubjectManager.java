package University_Management.src.manager;

import University_Management.src.model.Subject;
import java.util.ArrayList;

public class SubjectManager {
    private ArrayList<Subject> subjects = new ArrayList<>();

    public ArrayList<Subject> addSubject(Subject sub) {
        subjects.add(sub);
        return subjects;
    }

    public ArrayList<Subject> editSubject(String name, int id) {
        for (Subject s : subjects) {
            if (s.getSubjectId() == id) {
                System.out.println("Tim thay mon hoc");
                s.setSubjectName(name);
            }
        }
        return subjects;
    }

    public ArrayList<Subject> deleteSubject(int id) {
        subjects.removeIf(s -> s.getSubjectId() == id);
        return subjects;
    }

    public void printSubjectList() {
        System.out.println("=== Subject List ===");
        for (Subject s : subjects) {
            System.out.println("Subject ID: " + s.getSubjectId() + ", Ten: " + s.getSubjectName());
        }
    }
}
