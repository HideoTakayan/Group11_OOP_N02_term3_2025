package University_Management.src;

import University_Management.src.model.Student;
import University_Management.src.model.Subject;
import University_Management.src.model.Lecturers;
import University_Management.src.model.User;
public class App {
    public static void main(String[] args) {
        Student student = new Student(1, "Nguyễn Văn An", "Nam", "2003-05-10");
        System.out.println("=== StudentTest ===");
        System.out.println("Mã sinh viên: " + student.getStudentId());
        System.out.println("Tên sinh viên: " + student.getName());
        System.out.println("Giới tính: " + student.getGender());
        System.out.println("Ngày sinh: " + student.getDateOfBirth());

        Subject subject = new Subject(101, "Lập trình Java", 3, 1);

        System.out.println("=== SubjectTest ===");
        System.out.println("Mã môn học: " + subject.getSubjectId());
        System.out.println("Tên môn học: " + subject.getSubjectName());
        System.out.println("Số tín chỉ: " + subject.getCredit());
        System.out.println("Mã giảng viên phụ trách: " + subject.getLecturerId());

        Lecturers teacher = new Lecturers(1, "Nguyễn Lệ Thu", "Nữ", "2003-05-10");

        System.out.println("=== TeacherTest ===");
        System.out.println("Mã giáo viên: " + teacher.getLecturerId());
        System.out.println("Tên giáo viên: " + teacher.getName());
        System.out.println("Giới tính: " + teacher.getGender());
        System.out.println("Ngày sinh: " + teacher.getDateOfBirth());
    }
}
