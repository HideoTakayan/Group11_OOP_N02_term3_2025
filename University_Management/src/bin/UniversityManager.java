package University_Management.src.bin;

import University_Management.src.model.Person;
import University_Management.src.model.Student;
import University_Management.src.model.Enrollment;
import University_Management.src.model.ExamSchedule;
import University_Management.src.model.Lecturers;
import University_Management.src.model.Subject;

import java.util.ArrayList;

public class UniversityManager {
    public ArrayList<Student> students = new ArrayList<>();
    public ArrayList<Lecturers> lecturers = new ArrayList<>();
    public ArrayList<Subject> subjects = new ArrayList<>();
    public ArrayList<ExamSchedule> examSchedules = new ArrayList<>();
    public ArrayList<Enrollment> enrollments = new ArrayList<>();

    public <T extends Person> ArrayList<T> addEntity(T obj, ArrayList<T> list) {
        list.add(obj);
        return list;
    }

    public <T extends Person> ArrayList<T> editEntity(String name, int id, ArrayList<T> list) {
        for (T entity : list) {
            if (entity.getId() == id) {
                entity.setName(name);
                System.out.println("Đã cập nhật: " + name);
            }
        }
        return list;
    }

    public <T extends Person> ArrayList<T> deleteEntity(int id, ArrayList<T> list) {
        list.removeIf(entity -> entity.getId() == id);
        return list;
    }

    public <T extends Person> void printEntityList(ArrayList<T> list, String title) {
        System.out.println("=== " + title + " ===");
        for (T entity : list) {
            System.out.println("ID: " + entity.getId() + ", Tên: " + entity.getName());
        }
    }

    public void addExamSchedule(ExamSchedule schedule) {
        examSchedules.add(schedule);
        System.out.println("✔ Đã thêm lịch thi: " + schedule);
    }

    public void showAllExamSchedules() {
        System.out.println("=== Danh sách lịch thi ===");
        for (ExamSchedule exam : examSchedules) {
            System.out.println(exam);
        }
    }

    public void searchExamByDate(String date) {
        System.out.println("=== Lịch thi vào ngày: " + date + " ===");
        for (ExamSchedule exam : examSchedules) {
            if (exam.getExamDate().equals(date)) {
                System.out.println(exam);
            }
        }
    }

    public void addEnrollment(Enrollment e) {
        // Kiểm tra trùng lặp
        boolean exists = enrollments.stream()
                .anyMatch(en -> en.getStudentId() == e.getStudentId() && en.getSubjectId() == e.getSubjectId());
        if (!exists) {
            enrollments.add(e);
            System.out.println("✅ Đăng ký thành công!");
        } else {
            System.out.println("❌ Sinh viên đã đăng ký môn này!");
        }
    }

    public void deleteEnrollmentById(int enrollmentId) {
        enrollments.removeIf(e -> e.getId() == enrollmentId);
        System.out.println("✅ Đã xoá ghi danh ID: " + enrollmentId);
    }

    public void printEnrollments() {
        System.out.println("=== Danh sách ghi danh ===");
        for (Enrollment e : enrollments) {
            System.out.println("Enrollment ID: " + e.getId() +
                    ", Student ID: " + e.getStudentId() +
                    ", Subject ID: " + e.getSubjectId() +
                    ", Date: " + e.getEnrollmentDate());
        }
    }

    public boolean isEnrolled(int studentId, int subjectId) {
        return enrollments.stream().anyMatch(e -> e.getStudentId() == studentId && e.getSubjectId() == subjectId);
    }
}
