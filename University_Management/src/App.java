package University_Management.src;

import University_Management.src.manager.UniversityManager;
import University_Management.src.model.Student;
import University_Management.src.model.Lecturers;
import University_Management.src.model.Subject;

public class App {
    public static void main(String[] args) {
        UniversityManager um = new UniversityManager();

        // ===== Thêm Sinh Viên =====
        Student s1 = new Student(1, "Nguyen Van A", "Nam", "2000-01-01");
        Student s2 = new Student(2, "Tran Thi B", "Nữ", "2001-05-15");
        um.addEntity(s1, um.students);
        um.addEntity(s2, um.students);

        // ===== Thêm Giảng Viên =====
        Lecturers l1 = new Lecturers(101, "Le Van C", "Nam", "1975-03-20");
        Lecturers l2 = new Lecturers(102, "Pham Thi D", "Nữ", "1980-12-10");
        um.addEntity(l1, um.lecturers);
        um.addEntity(l2, um.lecturers);

        // ===== Thêm Môn Học =====
        Subject sub1 = new Subject(201, "Toán Cao Cấp", 3, 101);
        Subject sub2 = new Subject(202, "Vật Lý", 4, 102);
        um.addEntity(sub1, um.subjects);
        um.addEntity(sub2, um.subjects);

        // ===== In Danh Sách Ban Đầu =====
        System.out.println(">> Danh sách ban đầu:");
        um.printEntityList(um.students, "Sinh viên");
        um.printEntityList(um.lecturers, "Giảng viên");
        um.printEntityList(um.subjects, "Môn học");

        // ===== Sửa đổi =====
        System.out.println("\n>> Sửa đổi:");
        um.editEntity("Nguyen Van A Updated", 1, um.students);
        um.editEntity("Le Van C Updated", 101, um.lecturers);
        um.editEntity("Toán Cao Cấp Updated", 201, um.subjects);

        // ===== In Danh Sách Sau Sửa =====
        System.out.println("\n>> Danh sách sau sửa đổi:");
        um.printEntityList(um.students, "Sinh viên");
        um.printEntityList(um.lecturers, "Giảng viên");
        um.printEntityList(um.subjects, "Môn học");

        // ===== Xoá =====
        System.out.println("\n>> Xoá:");
        um.deleteEntity(2, um.students);
        um.deleteEntity(102, um.lecturers);
        um.deleteEntity(202, um.subjects);

        // ===== In Danh Sách Cuối Cùng =====
        System.out.println("\n>> Danh sách cuối cùng:");
        um.printEntityList(um.students, "Sinh viên");
        um.printEntityList(um.lecturers, "Giảng viên");
        um.printEntityList(um.subjects, "Môn học");
    }
}
