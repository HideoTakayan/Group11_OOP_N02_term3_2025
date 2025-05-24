package University_Management.test;

import University_Management.src.manager.UniversityManager;
import University_Management.src.model.Lecturers;

public class LecturerTest {

    public static void main(String[] args) {
        UniversityManager uniManager = new UniversityManager();

        // Thêm 3 giảng viên
        uniManager.addLecturer(new Lecturers(101, "Le Van A", "Male", "1980-05-10"));
        uniManager.addLecturer(new Lecturers(102, "Pham Thi B", "Female", "1985-07-20"));
        uniManager.addLecturer(new Lecturers(103, "Tran Van C", "Male", "1975-12-15"));

        System.out.println("Danh sach giang vien ban dau:");
        for (Lecturers l : uniManager.getAllLecturers()) {
            System.out.println(l.getLecturerId() + " - " + l.getName());
        }

        // Cập nhật giảng viên có ID 102
        boolean updated = uniManager.updateLecturer(102, "Pham Thi B Updated", "Female", "1985-07-21");
        System.out.println(updated ? "Cap nhat thanh cong giang vien ID 102" : "Khong tim thay giang vien ID 102");

        System.out.println("Danh sach giang vien sau khi cap nhat:");
        for (Lecturers l : uniManager.getAllLecturers()) {
            System.out.println(
                    l.getLecturerId() + " - " + l.getName() + ", " + l.getGender() + ", " + l.getDateOfBirth());
        }

        // Xóa giảng viên có ID 101
        boolean deleted = uniManager.deleteLecturer(101);
        System.out.println(deleted ? "Xoa thanh cong giang vien ID 101" : "Khong tim thay giang vien ID 101");

        System.out.println("Danh sach giang vien sau khi xoa:");
        for (Lecturers l : uniManager.getAllLecturers()) {
            System.out.println(l.getLecturerId() + " - " + l.getName());
        }
    }
}
