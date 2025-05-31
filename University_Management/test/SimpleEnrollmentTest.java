package University_Management.test;

import University_Management.src.model.Student;
import University_Management.src.model.Subject;
import University_Management.src.bin.EnrollmentManager;

public class SimpleEnrollmentTest {
    public static void main(String[] args) {
        // 1. Tạo dữ liệu test đơn giản
        Student student = new Student(1, "Nguyễn Văn A", "2000-01-01", "Nam");
        Subject math = new Subject(101, "Toán Cao Cấp", 3, 201);

        // 2. Khởi tạo EnrollmentManager
        EnrollmentManager em = new EnrollmentManager();

        // 3. Test đăng ký môn học
        System.out.println("=== TEST ĐƠN GIẢN ===");
        System.out.println("1. Đăng ký lần đầu:");
        boolean firstEnroll = em.enrollStudentToSubject(student, math) != null;
        System.out.println(firstEnroll ? "✅ Thành công" : "❌ Thất bại");

        System.out.println("\n2. Đăng ký lại cùng môn:");
        boolean duplicateEnroll = em.enrollStudentToSubject(student, math) == null;
        System.out.println(duplicateEnroll ? "✅ Phát hiện trùng" : "❌ Lỗi cho phép trùng");

        // 4. Kiểm tra nhanh
        System.out.println("\n3. Kiểm tra nhanh:");
        System.out.println("- SV " + student.getName() +
                " đã đăng ký Toán: " + em.isEnrolled(student.getId(), math.getId()));
    }
}