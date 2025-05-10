package University_Management.src.test;

import University_Management.src.model.Teacher;

public class TeacherTest {
    public static void main(String[] args) {
        Teacher teacher = new Teacher(1, "Nguyễn Lệ Thu", "Nữ", "2003-05-10");

        System.out.println("=== TeacherTest ===");
        System.out.println("Mã giáo viên: " + teacher.getLecturerId());
        System.out.println("Tên giáo viên: " + teacher.getName());
        System.out.println("Giới tính: " + teacher.getGender());
        System.out.println("Ngày sinh: " + teacher.getDateOfBirth());
    }
}
