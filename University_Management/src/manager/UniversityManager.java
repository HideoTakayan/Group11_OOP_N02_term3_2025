package University_Management.src.manager;

import University_Management.src.model.Student;
import University_Management.src.model.Lecturers;
import University_Management.src.model.Subject;

import java.util.ArrayList;

public class UniversityManager {
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Lecturers> lecturers = new ArrayList<>();
    ArrayList<Subject> subjects = new ArrayList<>();

    // ==== STUDENT CRUD ====
    public ArrayList<Student> addStudent(Student stu) {
        students.add(stu);
        return students;
    }

    public ArrayList<Student> editStudent(String name, int id) {
        for (Student s : students) {
            if (s.getStudentId() == id) {
                System.out.println("Found student to update.");
                s.setName(name);
            }
        }
        return students;
    }

    public ArrayList<Student> deleteStudent(int id) {
        students.removeIf(s -> s.getStudentId() == id);
        return students;
    }

    public void printStudentList() {
        System.out.println("=== Student List ===");
        for (Student s : students) {
            System.out.println("Student ID: " + s.getStudentId() + ", Fullname: " + s.getName());
        }
    }

    // ==== LECTURER CRUD ====
    public ArrayList<Lecturers> addLecturer(Lecturers lec) {
        lecturers.add(lec);
        return lecturers;
    }

    public ArrayList<Lecturers> editLecturer(String name, int id) {
        for (Lecturers l : lecturers) {
            if (l.getLecturerId() == id) {
                System.out.println("Found lecturer to update.");
                l.setName(name);
            }
        }
        return lecturers;
    }

    public ArrayList<Lecturers> deleteLecturer(int id) {
        lecturers.removeIf(l -> l.getLecturerId() == id);
        return lecturers;
    }

    public void printLecturerList() {
        System.out.println("=== Lecturer List ===");
        for (Lecturers l : lecturers) {
            System.out.println("Lecturer ID: " + l.getLecturerId() + ", Name: " + l.getName());
        }
    }

    // ==== SUBJECT CRUD ====
    public ArrayList<Subject> addSubject(Subject sub) {
        subjects.add(sub);
        return subjects;
    }

    public ArrayList<Subject> editSubject(String name, int id) {
        for (Subject s : subjects) {
            if (s.getSubjectId() == id) {
                System.out.println("Found subject to update.");
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
            System.out.println("Subject ID: " + s.getSubjectId() + ", Name: " + s.getSubjectName());
        }
    }
}
