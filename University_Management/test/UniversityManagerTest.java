package University_Management.test;

import University_Management.src.manager.UniversityManager;
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

        um.addEntity(s1, um.students);
        um.addEntity(s2, um.students);
        um.printEntityList(um.students, "Danh sách sinh viên");

        System.out.println("-- Edit sinh viên ID = 1 --");
        um.editEntity("Nguyen Van A (Đã sửa)", 1, um.students);
        um.printEntityList(um.students, "Danh sách sinh viên");

        System.out.println("-- Xoá sinh viên ID = 2 --");
        um.deleteEntity(2, um.students);
        um.printEntityList(um.students, "Danh sách sinh viên");

        // ===== TEST LECTURER =====
        System.out.println("\n>> TEST GIẢNG VIÊN <<");
        Lecturers l1 = new Lecturers(101, "Le Thi C", "Nữ", "20/09/1980");
        Lecturers l2 = new Lecturers(102, "Pham Van D", "Nam", "05/02/1975");

        um.addEntity(l1, um.lecturers);
        um.addEntity(l2, um.lecturers);
        um.printEntityList(um.lecturers, "Danh sách giảng viên");

        System.out.println("-- Edit giảng viên ID = 102 --");
        um.editEntity("Cô Mai (Đã sửa)", 102, um.lecturers);
        um.printEntityList(um.lecturers, "Danh sách giảng viên");

        System.out.println("-- Xoá giảng viên ID = 101 --");
        um.deleteEntity(101, um.lecturers);
        um.printEntityList(um.lecturers, "Danh sách giảng viên");

        // ===== TEST SUBJECT =====
        System.out.println("\n>> TEST MÔN HỌC <<");
        Subject sub1 = new Subject(201, "Toán cao cấp", 3, 102);
        Subject sub2 = new Subject(202, "Vật lý đại cương", 4, 101);

        um.addEntity(sub1, um.subjects);
        um.addEntity(sub2, um.subjects);
        um.printEntityList(um.subjects, "Danh sách môn học");

        System.out.println("-- Edit môn học ID = 202 --");
        um.editEntity("Vật lý đại cương (Đã sửa)", 202, um.subjects);
        um.printEntityList(um.subjects, "Danh sách môn học");

        System.out.println("-- Xoá môn học ID = 201 --");
        um.deleteEntity(201, um.subjects);
        um.printEntityList(um.subjects, "Danh sách môn học");
    }
}
