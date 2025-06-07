package bin;

import model.Enrollment;
import model.Student;
import model.Subject;

import java.time.LocalDate;
import java.util.List;

public class EnrollmentAdder {
    private List<Enrollment> enrollmentList;

    public EnrollmentAdder(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }

    public boolean addEnrollment(Student student, Subject subject, LocalDate enrollmentDate) {
        for (Enrollment e : enrollmentList) {
            if (e.getStudentID() == student.getStudentID() && e.getSubjectID() == subject.getSubjectID()) {
                System.out.println("Hoc sinh " + student.getStudentID() + " da dang ky mon " + subject.getSubjectID());
                return false;
            }
        }
        Enrollment enrollment = new Enrollment(student, subject, enrollmentDate);
        enrollmentList.add(enrollment);
        System.out.println("Da them dang ky: " + enrollment);
        return true;
    }
}
