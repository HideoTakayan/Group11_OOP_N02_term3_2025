package University_Management.test;

import University_Management.src.model.Student;
import University_Management.src.model.Subject;
import University_Management.src.model.Enrollment;
import University_Management.src.bin.UniversityManager;

import java.time.LocalDate;

public class SimpleEnrollmentTest {
    public static void main(String[] args) {
        UniversityManager manager = new UniversityManager();
        Student student = new Student(1, "Nguyễn Văn A", "2000-01-01", "Nam");
        Subject math = new Subject(101, "Toán Cao Cấp", 3, 201);
        manager.students.add(student);
        manager.subjects.add(math);
        System.out.println("=== TEST GHI DANH ===");
        System.out.println("1. Đăng ký lần đầu:");
        Enrollment e1 = new Enrollment(1, student.getId(), math.getId(), LocalDate.now());
        manager.addEnrollment(e1);
        System.out.println("\n2. Đăng ký lại cùng môn:");
        Enrollment e2 = new Enrollment(2, student.getId(), math.getId(), LocalDate.now());
        manager.addEnrollment(e2);
        System.out.println("\n3. Kiểm tra nhanh:");
        boolean enrolled = manager.isEnrolled(student.getId(), math.getId());
        System.out.println("- SV " + student.getName() +
                " đã đăng ký môn " + math.getName() + ": " + (enrolled ? "✅ Có" : "❌ Không"));

        System.out.println("\n4. Danh sách ghi danh:");
        manager.printEnrollments();
    }
}