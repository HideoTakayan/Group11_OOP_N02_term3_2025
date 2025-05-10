package StudentManagement.src.test;

import java.time.LocalDate;

import StudentManagement.src.model.Teacher;

public class TeacherTest {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("T01", "Nguyễn Lệ Thu", "Nữ", LocalDate.of(2003, 5, 10));

        System.out.println("=== TeacherTest ===");
        System.out.println("Mã giáo viên: " + teacher.getId());
        System.out.println("Tên giáo viên: " + teacher.getName());
        System.out.println("Giới tính: " + teacher.getGender());
        System.out.println("Ngày sinh: " + teacher.getDob());
    }
}
