package bin;

import model.Student;
import model.Lecturer;
import model.Subject;
import model.Grade;

import java.util.ArrayList;

public class UniversityManager {
    public ArrayList<Student> students = new ArrayList<>();
    public ArrayList<Lecturer> lecturers = new ArrayList<>();
    public ArrayList<Subject> subjects = new ArrayList<>();
    public ArrayList<Grade> grades = new ArrayList<>();

    // ===== Student methods =====
    public void addStudent(Student s) {
        students.add(s);
    }

    public void editStudentName(int id, String newName) {
        for (Student s : students) {
            if (s.getStudentID() == id) {
                s.setName(newName);
                System.out.println("Đã cập nhật tên sinh viên: " + newName);
                return;
            }
        }
        System.out.println("Không tìm thấy sinh viên với ID: " + id);
    }

    public void deleteStudent(int id) {
        boolean removed = students.removeIf(s -> s.getStudentID() == id);
        if (removed) {
            System.out.println("Đã xoá sinh viên với ID: " + id);
        } else {
            System.out.println("Không tìm thấy sinh viên với ID: " + id);
        }
    }

    public void printStudents() {
        System.out.println("=== Danh sách Sinh viên ===");
        for (Student s : students) {
            System.out.println("ID: " + s.getStudentID() + ", Tên: " + s.getName());
        }
    }

    // ===== Lecturer methods =====
    public void addLecturer(Lecturer l) {
        lecturers.add(l);
    }

    public void editLecturerName(int id, String newName) {
        for (Lecturer l : lecturers) {
            if (l.getLecturerID() == id) {
                l.setName(newName);
                System.out.println("Đã cập nhật tên giảng viên: " + newName);
                return;
            }
        }
        System.out.println("Không tìm thấy giảng viên với ID: " + id);
    }

    public void deleteLecturer(int id) {
        boolean removed = lecturers.removeIf(l -> l.getLecturerID() == id);
        if (removed) {
            System.out.println("Đã xoá giảng viên với ID: " + id);
        } else {
            System.out.println("Không tìm thấy giảng viên với ID: " + id);
        }
    }

    public void printLecturers() {
        System.out.println("=== Danh sách Giảng viên ===");
        for (Lecturer l : lecturers) {
            System.out.println("ID: " + l.getLecturerID() + ", Tên: " + l.getName());
        }
    }

    // ===== Subject methods =====
    public void addSubject(Subject sub) {
        subjects.add(sub);
    }

    public void editSubjectName(int id, String newName) {
        for (Subject sub : subjects) {
            if (sub.getSubjectID() == id) {
                sub.setSubjectName(newName);
                System.out.println("Đã cập nhật tên môn học: " + newName);
                return;
            }
        }
        System.out.println("Không tìm thấy môn học với ID: " + id);
    }

    public void deleteSubject(int id) {
        boolean removed = subjects.removeIf(sub -> sub.getSubjectID() == id);
        if (removed) {
            System.out.println("Đã xoá môn học với ID: " + id);
        } else {
            System.out.println("Không tìm thấy môn học với ID: " + id);
        }
    }

    public void printSubjects() {
        System.out.println("=== Danh sách Môn học ===");
        for (Subject sub : subjects) {
            System.out.println("ID: " + sub.getSubjectID() + ", Tên: " + sub.getSubjectName() + ", Giảng viên: "
                    + (sub.getLecturer() != null ? sub.getLecturer().getName() : "Chưa gán"));
        }
    }

    // ===== Grade methods (ví dụ) =====
    public void addGrade(Grade g) {
        grades.add(g);
    }

    public void printGrades() {
        System.out.println("=== Danh sách Điểm ===");
        for (Grade g : grades) {
            System.out.println("Student ID: " + g.getStudentID() + ", Subject ID: " + g.getSubjectId() + ", Score: "
                    + g.getScore());
        }
    }
}
