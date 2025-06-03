package University_Management.src.bin;

import University_Management.src.model.Enrollment;
import University_Management.src.model.Student;
import University_Management.src.model.Subject;
import java.time.LocalDate;
import java.util.ArrayList;

public class EnrollmentManager {
    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    // Đăng ký môn học
    public Enrollment enrollStudentToSubject(Student student, Subject subject) {
        if (isEnrolled(student.getStudentID(), subject.getSubjectID())) {
            System.out.println("Sinh viên " + student.getName() + " đã đăng ký môn " + subject.getSubjectName());
            return null;
        }

        Enrollment newEnrollment = new Enrollment(
                generateNewId(),
                student.getStudentID(),
                subject.getSubjectID(),
                LocalDate.now());

        enrollments.add(newEnrollment);
        System.out.println("Đăng ký thành công!");
        return newEnrollment;
    }

    // Hủy đăng ký theo ID ghi danh
    public boolean cancelEnrollment(int enrollmentId) {
        boolean removed = enrollments.removeIf(e -> e.getId() == enrollmentId);
        if (removed) {
            System.out.println("Đã hủy đăng ký với ID: " + enrollmentId);
        } else {
            System.out.println("Không tìm thấy đăng ký với ID: " + enrollmentId);
        }
        return removed;
    }

    // Kiểm tra đã đăng ký môn học chưa
    public boolean isEnrolled(int studentId, int subjectId) {
        return enrollments.stream()
                .anyMatch(e -> e.getStudentId() == studentId && e.getSubjectId() == subjectId);
    }

    // Lấy danh sách tất cả ghi danh
    public ArrayList<Enrollment> getAllEnrollments() {
        return enrollments;
    }

    // In danh sách ghi danh chi tiết
    public void printEnrollments() {
        System.out.println("=== Danh sách ghi danh ===");
        if (enrollments.isEmpty()) {
            System.out.println("Chưa có ghi danh nào.");
            return;
        }
        for (Enrollment e : enrollments) {
            System.out.println("Enrollment ID: " + e.getId() +
                    ", Student ID: " + e.getStudentId() +
                    ", Subject ID: " + e.getSubjectId() +
                    ", Ngày đăng ký: " + e.getEnrollmentDate());
        }
    }

    // Tạo ID mới tự động tăng
    private int generateNewId() {
        return enrollments.isEmpty() ? 1 : enrollments.get(enrollments.size() - 1).getId() + 1;
    }
}
