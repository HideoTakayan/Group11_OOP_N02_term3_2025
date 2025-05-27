package University_Management.test;

import University_Management.src.bin.UniversityManager;
import University_Management.src.model.Student;
import University_Management.src.model.Lecturers;
import University_Management.src.model.Subject;

public class UniversityManagerTest {
    public static void main(String[] args) {
        UniversityManager um = new UniversityManager();

        // ===== TEST STUDENT =====
        System.out.println(">> TEST SINH VIÊN <<");
        Student s1 = new Student(1, "Nguyen Van A", "Nam", "01/01/2004");
        Student s2 = new Student(2, "Tran Thi B", "Nữ", "15/06/2003");
        um.addStudent(s1);
        um.addStudent(s2);
        um.printStudentList();

        System.out.println("-- Edit sinh viên ID = 1 --");
        um.editStudent("Nguyen Van A (Đã sửa)", 1);
        um.printStudentList();

        System.out.println("-- Xoá sinh viên ID = 2 --");
        um.deleteStudent(2);
        um.printStudentList();

        // ===== TEST LECTURER =====
        System.out.println("\n>> TEST GIẢNG VIÊN <<");
        Lecturers l1 = new Lecturers(101, "Le Thi C", "Nữ", "20/09/1980");
        Lecturers l2 = new Lecturers(102, "Pham Van D", "Nam", "05/02/1975");
        um.addLecturer(l1);
        um.addLecturer(l2);
        um.printLecturerList();

        System.out.println("-- Edit giảng viên ID = 102 --");
        um.editLecturer("Cô Mai (Đã sửa)", 102);
        um.printLecturerList();

        System.out.println("-- Xoá giảng viên ID = 101 --");
        um.deleteLecturer(101);
        um.printLecturerList();

        // ===== TEST SUBJECT =====
        System.out.println("\n>> TEST MÔN HỌC <<");
        Subject sub1 = new Subject(201, "Toán cao cấp", 3, 102);
        Subject sub2 = new Subject(202, "Vật lý đại cương", 4, 101);
        um.addSubject(sub1);
        um.addSubject(sub2);
        um.printSubjectList();

        System.out.println("-- Edit môn học ID = 202 --");
        um.editSubject("Vật lý đại cương (Đã sửa)", 202);
        um.printSubjectList();

        System.out.println("-- Xoá môn học ID = 201 --");
        um.deleteSubject(201);
        um.printSubjectList();
    }
}
