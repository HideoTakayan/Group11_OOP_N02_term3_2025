package University_Management.test;

import University_Management.src.model.Subject;

public class SubjectTest {
    public static void main(String[] args) {
        // Tạo đối tượng Subject
        Subject sub1 = new Subject(201, "Toán Cao Cấp", 3, 101);

        // In thông tin ban đầu
        System.out.println("=== Thông tin môn học ban đầu ===");
        System.out.println("Mã môn: " + sub1.getId());
        System.out.println("Tên môn: " + sub1.getSubjectName());
        System.out.println("Số tín chỉ: " + sub1.getCredit());
        System.out.println("Mã giảng viên: " + sub1.getLecturerId());

        // Thay đổi thông tin
        sub1.setSubjectName("Giải tích");
        sub1.setCredit(4);
        sub1.setLecturerId(102);

        // In thông tin sau khi chỉnh sửa
        System.out.println("\n=== Sau khi chỉnh sửa ===");
        System.out.println("Mã môn: " + sub1.getId());
        System.out.println("Tên môn mới: " + sub1.getSubjectName());
        System.out.println("Số tín chỉ mới: " + sub1.getCredit());
        System.out.println("Mã giảng viên mới: " + sub1.getLecturerId());
    }
}
