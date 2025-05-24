package University_Management.test;

import University_Management.src.manager.LecturerManager;
import University_Management.src.model.Lecturers;

public class LecturerTest {
    public static void main(String[] args) {
        LecturerManager manager = new LecturerManager();

        // Thêm giảng viên
        Lecturers lec1 = new Lecturers(1, "Nguyen Van A", "Nam", "1980-05-12");
        Lecturers lec2 = new Lecturers(2, "Tran Thi B", "Nu", "1985-08-20");

        manager.addLecturer(lec1);
        manager.addLecturer(lec2);

        // In danh sách giảng viên sau khi thêm
        System.out.println("\nDanh sách giảng viên sau khi thêm:");
        manager.printLecturerList();

        // Sửa tên giảng viên có ID = 1
        manager.editLecturer("Nguyen Van A (Updated)", 1);

        // In danh sách sau khi sửa
        System.out.println("\nDanh sách giảng viên sau khi sửa:");
        manager.printLecturerList();

        // Xóa giảng viên có ID = 2
        manager.deleteLecturer(2);

        // In danh sách sau khi xóa
        System.out.println("\nDanh sách giảng viên sau khi xóa:");
        manager.printLecturerList();
    }
}
