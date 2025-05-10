package University_Management.src.test;

import University_Management.src.model.Subject;

public class SubjectTest {
    public static void main(String[] args) {
        Subject subject = new Subject(101, "Lập trình Java", 3, 1);

        System.out.println("=== SubjectTest ===");
        System.out.println("Mã môn học: " + subject.getSubjectId());
        System.out.println("Tên môn học: " + subject.getSubjectName());
        System.out.println("Số tín chỉ: " + subject.getCredit());
        System.out.println("Mã giảng viên phụ trách: " + subject.getLecturerId());
    }
}
