package bin;

import model.Enrollment;
import model.Student;
import model.Subject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnrollmentManager {
    private List<Enrollment> enrollments;
    private int nextId;

    public EnrollmentManager() {
        this.enrollments = new ArrayList<>();
        this.nextId = 1;
    }

    /**
     * Đăng ký môn học cho sinh viên
     * 
     * @param student Sinh viên cần đăng ký
     * @param subject Môn học cần đăng ký
     * @return Optional chứa Enrollment nếu thành công, empty nếu thất bại
     */
    public Optional<Enrollment> enrollStudentToSubject(Student student, Subject subject) {
        if (student == null || subject == null) {
            System.err.println("Sinh viên hoặc môn học không hợp lệ");
            return Optional.empty();
        }

        if (isEnrolled(student.getStudentID(), subject.getSubjectID())) {
            System.err.printf("Sinh viên %s (ID: %d) đã đăng ký môn %s (ID: %d)%n",
                    student.getName(), student.getStudentID(),
                    subject.getSubjectName(), subject.getSubjectID());
            return Optional.empty();
        }

        Enrollment newEnrollment = new Enrollment(
                nextId++,
                student.getStudentID(),
                subject.getSubjectID(),
                LocalDate.now());

        enrollments.add(newEnrollment);
        System.out.printf("Đăng ký thành công! Sinh viên %s - Môn %s%n",
                student.getName(), subject.getSubjectName());
        return Optional.of(newEnrollment);
    }

    /**
     * Hủy đăng ký môn học
     * 
     * @param enrollmentId ID của bản ghi đăng ký cần hủy
     * @return true nếu hủy thành công, false nếu không tìm thấy
     */
    public boolean cancelEnrollment(int enrollmentId) {
        boolean removed = enrollments.removeIf(e -> e.getId() == enrollmentId);
        if (removed) {
            System.out.println("Đã hủy đăng ký thành công với ID: " + enrollmentId);
        } else {
            System.err.println("Không tìm thấy đăng ký với ID: " + enrollmentId);
        }
        return removed;
    }

    /**
     * Kiểm tra sinh viên đã đăng ký môn học chưa
     * 
     * @param studentId ID sinh viên
     * @param subjectId ID môn học
     * @return true nếu đã đăng ký, false nếu chưa
     */
    public boolean isEnrolled(int studentId, int subjectId) {
        return enrollments.stream()
                .anyMatch(e -> e.getStudentId() == studentId && e.getSubjectId() == subjectId);
    }

    /**
     * Lấy danh sách tất cả đăng ký
     * 
     * @return Danh sách không thể thay đổi (immutable) các đăng ký
     */
    public List<Enrollment> getAllEnrollments() {
        return List.copyOf(enrollments);
    }

    /**
     * Lấy thông tin đăng ký theo ID
     * 
     * @param enrollmentId ID đăng ký cần tìm
     * @return Optional chứa Enrollment nếu tìm thấy
     */
    public Optional<Enrollment> getEnrollmentById(int enrollmentId) {
        return enrollments.stream()
                .filter(e -> e.getId() == enrollmentId)
                .findFirst();
    }

    /**
     * In danh sách đăng ký với thông tin chi tiết
     */
    public void printEnrollments() {
        System.out.println("\n=== DANH SÁCH ĐĂNG KÝ MÔN HỌC ===");
        System.out.printf("%-12s %-20s %-20s %-15s%n",
                "Mã ĐK", "Sinh viên", "Môn học", "Ngày đăng ký");
        System.out.println("------------------------------------------------------------");

        if (enrollments.isEmpty()) {
            System.out.println("Chưa có đăng ký nào");
            return;
        }

        for (Enrollment e : enrollments) {
            System.out.printf("%-12d %-20d %-20d %-15s%n",
                    e.getId(),
                    e.getStudentId(),
                    e.getSubjectId(),
                    e.getEnrollmentDate());
        }
        System.out.println("Tổng số: " + enrollments.size() + " đăng ký\n");
    }

    /**
     * Lấy danh sách đăng ký của một sinh viên
     * 
     * @param studentId ID sinh viên
     * @return Danh sách các đăng ký của sinh viên
     */
    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        return enrollments.stream()
                .filter(e -> e.getStudentId() == studentId)
                .toList();
    }

    /**
     * Lấy danh sách đăng ký của một môn học
     * 
     * @param subjectId ID môn học
     * @return Danh sách các đăng ký của môn học
     */
    public List<Enrollment> getEnrollmentsBySubject(int subjectId) {
        return enrollments.stream()
                .filter(e -> e.getSubjectId() == subjectId)
                .toList();
    }
}