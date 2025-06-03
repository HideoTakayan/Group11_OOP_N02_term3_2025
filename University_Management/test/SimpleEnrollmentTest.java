package University_Management.test;

import bin.EnrollmentManager;
import model.Enrollment;
import model.Student;
import model.Subject;

public class SimpleEnrollmentTest {
    public static void main(String[] args) {
        EnrollmentManager manager = new EnrollmentManager();

        // Tao du lieu mau
        Student student1 = new Student(1, "Nguyen Van A", "Nam", "2000-01-01");
        Subject subject1 = new Subject(101, "Lap trinh Java", 3, 201);
        Subject subject2 = new Subject(102, "Cau truc du lieu", 3, 202);

        // Test dang ky mon hoc
        System.out.println("\n--- Dang ky mon hoc ---");
        manager.enrollStudentToSubject(student1, subject1); // Lan 1
        manager.enrollStudentToSubject(student1, subject1); // Trung lap
        manager.enrollStudentToSubject(student1, subject2); // Mon khac

        // Test kiem tra da dang ky chua
        System.out.println("\n--- Kiem tra dang ky ---");
        System.out.println("Da dang ky mon 101? " + manager.isEnrolled(student1.getId(), 101));
        System.out.println("Da dang ky mon 999? " + manager.isEnrolled(student1.getId(), 999));

        // Test huy dang ky
        System.out.println("\n--- Huy dang ky ---");
        boolean canceled = manager.cancelEnrollment(1);
        System.out.println("Huy dang ky ID 1: " + (canceled ? "Thanh cong" : "Khong tim thay"));

        // Kiem tra lai sau khi huy
        System.out.println("Da dang ky mon 101? " + manager.isEnrolled(student1.getId(), 101));
    }
}
