package University_Management.test;

import University_Management.src.model.Subject;
import University_Management.src.model.Lecturer;

import java.time.LocalDate;

public class SubjectTest {
    public static void main(String[] args) {
        // Tạo Lecturer với đầy đủ thông tin
        Lecturer lecturer = new Lecturer(1, "Nguyen Van A", LocalDate.of(1980, 5, 20), "Nam");

        // Tạo Subject với Lecturer trên
        Subject subject = new Subject(101, "Lập trình Java", 3, lecturer);

        // In ra thông tin chi tiết của Subject
        System.out.println("=== Thông tin môn học ===");
        System.out.println("Mã môn: " + subject.getSubjectID());
        System.out.println("Tên môn: " + subject.getSubjectName());
        System.out.println("Số tín chỉ: " + subject.getCredits());

        // In thông tin giảng viên phụ trách môn học
        System.out.println("\n=== Thông tin giảng viên ===");
        System.out.println("Mã giảng viên: " + lecturer.getLecturerID());
        System.out.println("Tên giảng viên: " + lecturer.getName());
        System.out.println("Ngày sinh: " + lecturer.getDateOfBirth());
        System.out.println("Giới tính: " + lecturer.getGender());
    }
}
