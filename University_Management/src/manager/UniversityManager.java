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
    public ArrayList<Student> addStudent(Student s) {
        students.add(s);
        return students;
    }

    public ArrayList<Student> editStudent(String name, int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() == id) {
                System.out.println("Tìm thấy sinh viên");
                students.get(i).setName(name);
            }
        }
        return students;
    }

    public ArrayList<Student> deleteStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() == id) {
                students.remove(i);
                break;
            }
        }
        return students;
    }

    public void printStudentList() {
        System.out.println("=== Danh sách sinh viên ===");
        for (Student s : students) {
            System.out.println("Student ID: " + s.getStudentId()
                    + ", Tên: " + s.getName()
                    + ", Giới tính: " + s.getGender()
                    + ", Ngày sinh: " + s.getDateOfBirth());
        }
    }

    // ==== LECTURER CRUD ====
    public ArrayList<Lecturers> addLecturer(Lecturers l) {
        lecturers.add(l);
        return lecturers;
    }

    public ArrayList<Lecturers> editLecturer(String name, int id) {
        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i).getLecturerId() == id) {
                System.out.println("Tìm thấy giảng viên");
                lecturers.get(i).setName(name);
            }
        }
        return lecturers;
    }

    public ArrayList<Lecturers> deleteLecturer(int id) {
        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i).getLecturerId() == id) {
                lecturers.remove(i);
                break;
            }
        }
        return lecturers;
    }

    public void printLecturerList() {
        System.out.println("=== Danh sách sinh viên ===");
        for (Lecturers l : lecturers) {
            System.out.println("Student ID: " + l.getLecturerId()
                    + ", Tên: " + l.getName()
                    + ", Giới tính: " + l.getGender()
                    + ", Ngày sinh: " + l.getDateOfBirth());
        }
    }

    // ==== SUBJECT CRUD ====
    public ArrayList<Subject> addSubject(Subject s) {
        subjects.add(s);
        return subjects;
    }

    public ArrayList<Subject> editSubject(String name, int id) {
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getSubjectId() == id) {
                System.out.println("Tìm thấy môn học");
                subjects.get(i).setSubjectName(name);
            }
        }
        return subjects;
    }

    public ArrayList<Subject> deleteSubject(int id) {
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getSubjectId() == id) {
                subjects.remove(i);
                break;
            }
        }
        return subjects;
    }

    public void printSubjectList() {
        System.out.println("=== Danh sách môn học ===");
        for (Subject s : subjects) {
            System.out.println("Subject ID: " + s.getSubjectId() + ", Tên: " + s.getSubjectName());
        }
    }
}
