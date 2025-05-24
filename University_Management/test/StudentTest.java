package University_Management.test;

import University_Management.src.manager.StudentManager;
import University_Management.src.model.Student;

public class StudentTest {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        // Thêm sinh viên
        Student stu1 = new Student(101, "Le Thi A", "Nu", "2002-01-15");
        Student stu2 = new Student(102, "Nguyen Van B", "Nam", "2001-11-20");

        manager.addStudent(stu1);
        manager.addStudent(stu2);

        // In danh sách sinh viên sau khi thêm
        System.out.println("\nDanh sách sinh viên sau khi thêm:");
        manager.printStudentList();

        // Sửa tên sinh viên có ID = 101
        manager.editStudent("Le Thi A (Updated)", 101);

        // In danh sách sau khi sửa
        System.out.println("\nDanh sách sinh viên sau khi sửa:");
        manager.printStudentList();

        // Xóa sinh viên có ID = 102
        manager.deleteStudent(102);

        // In danh sách sau khi xóa
        System.out.println("\nDanh sách sinh viên sau khi xóa:");
        manager.printStudentList();
    }
}
