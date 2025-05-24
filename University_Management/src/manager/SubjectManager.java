package University_Management.src.manager;

import java.util.ArrayList;
import java.util.List;
import University_Management.src.model.Subject;

public class SubjectManager {
    private List<Subject> subjects = new ArrayList<>();

    public void addSubject(Subject s) {
        subjects.add(s);
    }

    public List<Subject> getAllSubjects() {
        return subjects;
    }

    public Subject findSubjectById(int id) {
        for (Subject s : subjects) {
            if (s.getSubjectId() == id)
                return s;
        }
        return null;
    }

    public boolean updateSubject(int id, String name, int credit, int lecturerId) {
        Subject s = findSubjectById(id);
        if (s == null)
            return false;

        s.setSubjectName(name);
        s.setCredit(credit);
        s.setLecturerId(lecturerId);
        return true;
    }

    public boolean deleteSubject(int id) {
        Subject s = findSubjectById(id);
        if (s == null)
            return false;
        subjects.remove(s);
        return true;
    }
}
