package University_Management.test;
import University_Management.src.dao.GradeDAO;
import University_Management.src.model.Student;
import java.util.List;
import University_Management.src.model.Grade;

public class StudentTest {
    public static void main(String[] args) {
        // Tạo đối tượng Student
        Student s1 = new Student(1, "Nguyen Van A", "Nam", "01/01/2000");

        // In thông tin ban đầu
        System.out.println("=== Thông tin sinh viên ban đầu ===");
        System.out.println("ID: " + s1.getId());
        System.out.println("Tên: " + s1.getName());
        System.out.println("Giới tính: " + s1.getGender());
        System.out.println("Ngày sinh: " + s1.getDateOfBirth());
        GradeDAO gradeDAO = new GradeDAO();
        List<Grade> grades = gradeDAO.getGradesByStudentID(1);

        System.out.println("\n=== Danh sách điểm của sinh viên ID 1 ===");
        for (Grade g : grades) {
        System.out.println("Môn: " + g.getSubjectId() + " | Điểm: " + g.getScore());
        }

    }

}
