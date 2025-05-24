package University_Management.test;

import University_Management.src.model.Lecturers;

public class LecturerTest {
    public static void main(String[] args) {
        Lecturers teacher = new Lecturers(1, "Nguyễn Lệ Thu", "Nữ", "2003-05-10");
        System.out.println("=== TeacherTest ===");
        System.out.println("Mã giáo viên: " + teacher.getLecturerId());
        System.out.println("Tên giáo viên: " + teacher.getName());
        System.out.println("Giới tính: " + teacher.getGender());
        System.out.println("Ngày sinh: " + teacher.getDateOfBirth());
    }
}
