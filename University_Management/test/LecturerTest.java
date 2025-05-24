package University_Management.test;

import University_Management.src.model.Lecturers;

public class LecturerTest {
    public static void main(String[] args) {
        // Tạo đối tượng Lecturers
        Lecturers lec1 = new Lecturers(101, "Tran Van B", "Nam", "15/03/1975");

        System.out.println("=== Thông tin giảng viên ban đầu ===");
        System.out.println("ID: " + lec1.getLecturerId());
        System.out.println("Tên: " + lec1.getName());
        System.out.println("Giới tính: " + lec1.getGender());
        System.out.println("Ngày sinh: " + lec1.getDateOfBirth());
    }
}
