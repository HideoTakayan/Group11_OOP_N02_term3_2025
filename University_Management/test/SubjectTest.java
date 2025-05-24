package University_Management.test;

import University_Management.src.model.Subject;

public class SubjectTest {
    public static void main(String[] args) {
        Subject sub1 = new Subject(201, "Toán Cao Cấp", 3, 101);
        System.out.println("=== Thông tin môn học ban đầu ===");
        System.out.println("Mã môn: " + sub1.getSubjectId());
        System.out.println("Tên môn: " + sub1.getSubjectName());
        System.out.println("Số tín chỉ: " + sub1.getCredit());
        System.out.println("Mã giảng viên: " + sub1.getLecturerId());
    }
}
