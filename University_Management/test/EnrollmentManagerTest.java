package University_Management.test;

import bin.EnrollmentManager;
import model.Enrollment;
import model.Student;
import model.Subject;
import model.Lecturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class EnrollmentManagerTest {
    private EnrollmentManager manager;
    private Student student;
    private Subject subject1;
    private Subject subject2;
    private Lecturer lecturer;

    @BeforeEach
    void setUp() {
        manager = new EnrollmentManager();
        lecturer = new Lecturer(201, "TS. Tran Van B", LocalDate.of(1980, 5, 15), "Nam");
        student = new Student(1, "Nguyen Van A", LocalDate.of(2000, 1, 1), "Nam");
        subject1 = new Subject(101, "Lap trinh Java", 3, lecturer);
        subject2 = new Subject(102, "Cau truc du lieu", 3, lecturer);
    }

    @Test
    void testEnrollStudentToSubject_Success() {
        assertNotNull(manager.enrollStudentToSubject(student, subject1));
        assertEquals(1, manager.getAllEnrollments().size());
    }

    @Test
    void testEnrollStudentToSubject_Duplicate() {
        manager.enrollStudentToSubject(student, subject1);
        assertNull(manager.enrollStudentToSubject(student, subject1));
        assertEquals(1, manager.getAllEnrollments().size());
    }

    @Test
    void testEnrollStudentToSubject_DifferentSubjects() {
        manager.enrollStudentToSubject(student, subject1);
        assertNotNull(manager.enrollStudentToSubject(student, subject2));
        assertEquals(2, manager.getAllEnrollments().size());
    }

    @Test
    void testIsEnrolled_WhenEnrolled() {
        manager.enrollStudentToSubject(student, subject1);
        assertTrue(manager.isEnrolled(student.getStudentID(), subject1.getSubjectID()));
    }

    @Test
    void testIsEnrolled_WhenNotEnrolled() {
        assertFalse(manager.isEnrolled(student.getStudentID(), 999));
    }

    @Test
    void testCancelEnrollment_Success() {
        manager.enrollStudentToSubject(student, subject1);
        int enrollmentId = manager.getAllEnrollments().get(0).getId();
        assertTrue(manager.cancelEnrollment(enrollmentId));
        assertEquals(0, manager.getAllEnrollments().size());
    }

    @Test
    void testCancelEnrollment_NotFound() {
        assertFalse(manager.cancelEnrollment(999));
    }

    @Test
    void testCancelEnrollment_VerifyAfterCancel() {
        manager.enrollStudentToSubject(student, subject1);
        int enrollmentId = manager.getAllEnrollments().get(0).getId();
        manager.cancelEnrollment(enrollmentId);
        assertFalse(manager.isEnrolled(student.getStudentID(), subject1.getSubjectID()));
    }

    @Test
    void testGetAllEnrollments_WhenEmpty() {
        assertTrue(manager.getAllEnrollments().isEmpty());
    }

    @Test
    void testGetAllEnrollments_AfterEnrollment() {
        manager.enrollStudentToSubject(student, subject1);
        manager.enrollStudentToSubject(student, subject2);
        assertEquals(2, manager.getAllEnrollments().size());
    }

    @Test
    void testIntegration_EnrollCancelReenroll() {
        // Bước 1: Đăng ký môn học
        Enrollment enrollment = manager.enrollStudentToSubject(student, subject1);
        assertNotNull(enrollment);
        
        // Bước 2: Hủy đăng ký
        assertTrue(manager.cancelEnrollment(enrollment.getId()));
        
        // Bước 3: Đăng ký lại
        Enrollment reenrollment = manager.enrollStudentToSubject(student, subject1);
        assertNotNull(reenrollment);
        assertEquals(1, manager.getAllEnrollments().size());
    }
}