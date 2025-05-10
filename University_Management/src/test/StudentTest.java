package University_Management.src.test;

import University_Management.src.model.Student;

public class StudentTest {
    public static void main(String[] args) {
        Student student = new Student(1, "Nguyễn Văn An", "Nam", "2003-05-10");
        System.out.println("=== StudentTest ===");
        System.out.println("Mã sinh viên: " + student.getStudentId());
        System.out.println("Tên sinh viên: " + student.getName());
        System.out.println("Giới tính: " + student.getGender());
        System.out.println("Ngày sinh: " + student.getDateOfBirth());
    }
}
