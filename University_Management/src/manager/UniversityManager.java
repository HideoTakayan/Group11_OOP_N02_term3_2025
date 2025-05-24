package University_Management.src.manager;

import University_Management.src.model.Student;
import University_Management.src.model.Lecturers;
import University_Management.src.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class UniversityManager {
    private List<Student> students = new ArrayList<>();
    private List<Lecturers> lecturers = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();

    // ==== Student CRUD ====
    public void addStudent(Student s) {
        students.add(s);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getStudentId() == id)
                return s;
        }
        return null;
    }

    public boolean updateStudent(int id, String name, String gender, String dob) {
        Student s = findStudentById(id);
        if (s == null)
            return false;
        students.remove(s);
        students.add(new Student(id, name, gender, dob));
        return true;
    }

    public boolean deleteStudent(int id) {
        Student s = findStudentById(id);
        if (s == null)
            return false;
        students.remove(s);
        return true;
    }

    // ==== Lecturer CRUD ====
    public void addLecturer(Lecturers l) {
        lecturers.add(l);
    }

    public List<Lecturers> getAllLecturers() {
        return lecturers;
    }

    public Lecturers findLecturerById(int id) {
        for (Lecturers l : lecturers) {
            if (l.getLecturerId() == id)
                return l;
        }
        return null;
    }

    public boolean updateLecturer(int id, String name, String gender, String dob) {
        Lecturers l = findLecturerById(id);
        if (l == null)
            return false;
        l.setName(name);
        l.setGender(gender);
        l.setDateOfBirth(dob);
        return true;
    }

    public boolean deleteLecturer(int id) {
        Lecturers l = findLecturerById(id);
        if (l == null)
            return false;
        lecturers.remove(l);
        return true;
    }

    // ==== Subject CRUD ====
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
