package StudentManagement.src.test;

import java.time.LocalDate;

import StudentManagement.src.model.Student;

public class StudentTest {
    public static void main(String[] args) {
        Student student = new Student("SV01", "Nguyễn Văn An", "Nam", LocalDate.of(2003, 5, 10));

        System.out.println("StudentTest");
        System.out.println("Mã sinh viên: " + student.getId());
        System.out.println("Tên sinh viên: " + student.getName());
        System.out.println("Giới tính: " + student.getGender());
        System.out.println("Ngày sinh: " + student.getDob());
    }
}
