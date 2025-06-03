package University_Management.test;

import bin.UniversityManager;
import model.Student;
import model.Lecturer;
import model.Subject;

import java.time.LocalDate;

public class UniversityManagerTest {
    public static void main(String[] args) {
        UniversityManager um = new UniversityManager();

        // ===== TEST STUDENT =====
        System.out.println(">> TEST SINH VIÊN <<");
        Student s1 = new Student(1, "Nguyen Van A", LocalDate.of(2004, 1, 1), "Nam");
        Student s2 = new Student(2, "Tran Thi B", LocalDate.of(2003, 6, 15), "Nữ");

        um.addStudent(s1);
        um.addStudent(s2);
        um.printStudents();

        System.out.println("-- Edit sinh viên ID = 1 --");
        um.editStudentName(1, "Nguyen Van A (Đã sửa)");
        um.printStudents();

        System.out.println("-- Xoá sinh viên ID = 2 --");
        um.deleteStudent(2);
        um.printStudents();

        // ===== TEST LECTURER =====
        System.out.println("\n>> TEST GIẢNG VIÊN <<");
        Lecturer l1 = new Lecturer(101, "Le Thi C", LocalDate.of(1980, 9, 20), "Nữ");
        Lecturer l2 = new Lecturer(102, "Pham Van D", LocalDate.of(1975, 2, 5), "Nam");

        um.addLecturer(l1);
        um.addLecturer(l2);
        um.printLecturers();

        System.out.println("-- Edit giảng viên ID = 102 --");
        um.editLecturerName(102, "Cô Mai (Đã sửa)");
        um.printLecturers();

        System.out.println("-- Xoá giảng viên ID = 101 --");
        um.deleteLecturer(101);
        um.printLecturers();

        // ===== TEST SUBJECT =====
        System.out.println("\n>> TEST MÔN HỌC <<");
        // Gán giảng viên đúng kiểu cho môn học
        Subject sub1 = new Subject(201, "Toán cao cấp", 3, l2);
        Subject sub2 = new Subject(202, "Vật lý đại cương", 4, l1);

        um.addSubject(sub1);
        um.addSubject(sub2);
        um.printSubjects();

        System.out.println("-- Edit môn học ID = 202 --");
        um.editSubjectName(202, "Vật lý đại cương (Đã sửa)");
        um.printSubjects();

        System.out.println("-- Xoá môn học ID = 201 --");
        um.deleteSubject(201);
        um.printSubjects();
    }
}
