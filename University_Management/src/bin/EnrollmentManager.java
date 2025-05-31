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
        if (isEnrolled(student.getId(), subject.getId())) {
            System.out.println("Sinh viên " + student.getName() + " đã đăng ký môn " + subject.getSubjectName());
            return null;
        }

        Enrollment newEnrollment = new Enrollment(
                generateNewId(),
                student.getId(),
                subject.getId(),
                LocalDate.now());

        enrollments.add(newEnrollment);
        System.out.println("Đăng ký thành công!");
        return newEnrollment;
    }

    // Hủy đăng ký
    public boolean cancelEnrollment(int enrollmentId) {
        return enrollments.removeIf(e -> e.getId() == enrollmentId);
    }

    // Kiểm tra đã đăng ký chưa
    public boolean isEnrolled(int studentId, int subjectId) {
        return enrollments.stream()
                .anyMatch(e -> e.getStudentId() == studentId && e.getSubjectId() == subjectId);
    }

    private int generateNewId() {
        return enrollments.isEmpty() ? 1 : enrollments.get(enrollments.size() - 1).getId() + 1;
    }
}
