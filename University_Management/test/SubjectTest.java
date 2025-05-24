package University_Management.test;

import University_Management.src.manager.SubjectManager;
import University_Management.src.model.Subject;

public class SubjectTest {
    public static void main(String[] args) {
        SubjectManager manager = new SubjectManager();

        // Thêm môn học
        Subject sub1 = new Subject(201, "Lap trinh Java", 3, 1);
        Subject sub2 = new Subject(202, "Cau truc du lieu", 4, 2);

        manager.addSubject(sub1);
        manager.addSubject(sub2);

        // In danh sách môn học sau khi thêm
        System.out.println("\nDanh sách môn học sau khi thêm:");
        manager.printSubjectList();

        // Sửa tên môn học có ID = 201
        manager.editSubject("Lap trinh Java (Updated)", 201);

        // In danh sách sau khi sửa
        System.out.println("\nDanh sách môn học sau khi sửa:");
        manager.printSubjectList();

        // Xóa môn học có ID = 202
        manager.deleteSubject(202);

        // In danh sách sau khi xóa
        System.out.println("\nDanh sách môn học sau khi xóa:");
        manager.printSubjectList();
    }
}
