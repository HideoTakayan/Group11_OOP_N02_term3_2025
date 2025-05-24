package University_Management.test;

import University_Management.src.manager.UniversityManager;
import University_Management.src.model.Student;

public class StudentTest {

    public static void main(String[] args) {
        UniversityManager uniManager = new UniversityManager();

        // Thêm sẵn 3 student
        uniManager.addStudent(new Student(12345, "Nguyen Thi Lan Anh", "Female", "2000-01-01"));
        uniManager.addStudent(new Student(1, "Tran Van Minh", "Male", "1999-02-02"));
        uniManager.addStudent(new Student(101010, "Nguyen An", "Male", "2001-03-03"));

        // In danh sách sinh viên
        System.out.println("Danh sach sinh vien ban dau:");
        for (Student s : uniManager.getAllStudents()) {
            System.out.println(s.getStudentId() + " - " + s.getName());
        }

        Scanner scanner = new Scanner(System.in);

        // Cập nhật thông tin sinh viên
        System.out.println("Nhap student ID can cap nhat:");
        int updateId = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        System.out.println("Nhap ten moi:");
        String newName = scanner.nextLine();
        System.out.println("Nhap gioi tinh moi:");
        String newGender = scanner.nextLine();
        System.out.println("Nhap ngay sinh moi (yyyy-MM-dd):");
        String newDob = scanner.nextLine();

        boolean updated = uniManager.updateStudent(updateId, newName, newGender, newDob);
        if (updated) {
            System.out.println("Cap nhat thanh cong.");
        } else {
            System.out.println("Khong tim thay sinh vien voi ID " + updateId);
        }

        // In lại danh sách sinh viên
        System.out.println("Danh sach sinh vien sau khi cap nhat:");
        for (Student s : uniManager.getAllStudents()) {
            System.out
                    .println(s.getStudentId() + " - " + s.getName() + ", " + s.getGender() + ", " + s.getDateOfBirth());
        }

        // Xóa sinh viên
        System.out.println("Nhap student ID can xoa:");
        int deleteId = scanner.nextInt();

        boolean deleted = uniManager.deleteStudent(deleteId);
        if (deleted) {
            System.out.println("Xoa thanh cong.");
        } else {
            System.out.println("Khong tim thay sinh vien voi ID " + deleteId);
        }

        // In danh sách sinh viên sau khi xóa
        System.out.println("Danh sach sinh vien sau khi xoa:");
        for (Student s : uniManager.getAllStudents()) {
            System.out.println(s.getStudentId() + " - " + s.getName());
        }

        scanner.close();
    }
}
