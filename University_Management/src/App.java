package University_Management.src;

import University_Management.src.manager.UniversityManager;

import University_Management.src.model.Lecturers;

import University_Management.src.model.Student;
// import University_Management.src.model.Lecturers;
import University_Management.src.model.Subject;

public class App {
    public static void main(String[] args) {
        UniversityManager um = new UniversityManager();
        Student s1 = new Student(1, "Nguyen Van A", "Nam", "2000-01-01");
        Student s2 = new Student(2, "Tran Thi B", "Nữ", "2001-05-15");
        um.addStudent(s1);
        um.addStudent(s2);

        // Thêm giảng viên
        Lecturers l1 = new Lecturers(101, "Le Van C", "Nam", "1975-03-20");
        Lecturers l2 = new Lecturers(102, "Pham Thi D", "Nữ", "1980-12-10");
        um.addLecturer(l1);
        um.addLecturer(l2);

        // Thêm môn học
        Subject sub1 = new Subject(201, "Toán Cao Cấp", 3, 101);
        Subject sub2 = new Subject(202, "Vật Lý", 4, 102);
        um.addSubject(sub1);
        um.addSubject(sub2);

        // In danh sách ban đầu
        um.printStudentList();
        um.printLecturerList();
        um.printSubjectList();

        System.out.println("\n--- Sửa đổi ---");

        // Sửa tên sinh viên
        um.editStudent("Nguyen Van A Updated", 1);
        // Sửa tên giảng viên
        um.editLecturer("Le Van C Updated", 101);
        // Sửa tên môn học
        um.editSubject("Toán Cao Cấp Updated", 201);

        // In danh sách sau sửa đổi
        um.printStudentList();
        um.printLecturerList();
        um.printSubjectList();

        System.out.println("\n--- Xóa ---");

        // Xóa sinh viên id=2
        um.deleteStudent(2);
        // Xóa giảng viên id=102
        um.deleteLecturer(102);
        // Xóa môn học id=202
        um.deleteSubject(202);

        // In danh sách cuối cùng
        um.printStudentList();
        um.printLecturerList();
        um.printSubjectList();
    }
}
