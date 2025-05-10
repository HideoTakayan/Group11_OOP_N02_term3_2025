package StudentManagement.src.test;

import java.time.LocalDate;

import StudentManagement.src.model.Subject;
import StudentManagement.src.model.Teacher;

public class SubjectTest {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("T02", "Nguyễn Lệ Thu", "Nữ", LocalDate.of(2003, 5, 10));
        Subject subject = new Subject("S01", "OOP", 3, teacher);

        System.out.println("SubjectTest");
        System.out.println("MÃ môn học: " + subject.getId());
        System.out.println("Tên môn học: " + subject.getName());
        System.out.println("Số tín " + subject.getCredit());
        System.out.println("Giáo viên phụ trách: " + subject.getTeacher().getName());
    }
}