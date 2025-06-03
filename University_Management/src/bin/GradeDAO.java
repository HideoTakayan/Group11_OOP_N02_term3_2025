package University_Management.test;

import University_Management.src.model.Student;
import java.time.LocalDate;
import java.util.*;

public class StudentTest {
    public static void main(String[] args) {
        // Tạo các đối tượng Student
        Student s1 = new Student(1001, "An", LocalDate.of(2002, 5, 10), "Male");
        Student s2 = new Student(1002, "Binh", LocalDate.of(2001, 3, 15), "Male");
        Student s3 = new Student(1003, "Chi", LocalDate.of(2003, 7, 20), "Female");

        // Thêm vào danh sách
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);

        // In ra danh sách ban đầu
        System.out.println("Danh sách sinh viên ban đầu:");
        for (Student s : students) {
            System.out.println(s);
        }

        // Sắp xếp theo tên
        students.sort(Comparator.comparing(Student::getName));

        // In ra danh sách sau khi sắp xếp
        System.out.println("\nDanh sách sinh viên sau khi sắp xếp theo tên:");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
