import java.time.LocalDate;
import bin.UniversityManager;
import model.Student;
import model.Lecturer;
import model.Subject;
import model.Person;
import bin.PersonService;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        PersonService service = new PersonService();

        service.addPerson(new Student(1, "Nguyen Van A", LocalDate.of(2000, 1, 1), "Male"));
        service.addPerson(new Lecturer("Le Thi B", LocalDate.of(1980, 5, 20), "Female", 1001));

        System.out.println("== Danh sách ban đầu ==");
        service.showAll();

        System.out.println("\n== Sau khi cập nhật tên sinh viên ID 1 ==");
        service.updateNameById(1, "Nguyen Van C");
        service.showAll();

        System.out.println("\n== Sau khi xóa giảng viên ID 1001 ==");
        service.deleteById(1001);
        service.showAll();
    }
}

/*
 * public class App {
 * public static void main(String[] args) {
 * UniversityManager um = new UniversityManager();
 * 
 * // ===== Thêm Sinh Viên =====
 * Student s1 = new Student(1, "Nguyen Van A", LocalDate.parse("2000-01-01"),
 * "Nam");
 * Student s2 = new Student(2, "Tran Thi B", LocalDate.parse("2001-05-15"),
 * "Nữ");
 * um.addEntity(s1, um.students);
 * um.addEntity(s2, um.students);
 * 
 * // ===== Thêm Giảng Viên =====
 * Lecturer l1 = new Lecturer(101, "Le Van C", LocalDate.parse("1975-03-20"),
 * "Nam");
 * Lecturer l2 = new Lecturer(102, "Pham Thi D", LocalDate.parse("1980-12-10"),
 * "Nữ");
 * um.addEntity(l1, um.lecturers);
 * um.addEntity(l2, um.lecturers);
 * 
 * // ===== Thêm Môn Học =====
 * Subject sub1 = new Subject(201, "Toán Cao Cấp", 3, l1);
 * Subject sub2 = new Subject(202, "Vật Lý", 4, l2);
 * um.addEntity(sub1, um.subjects);
 * um.addEntity(sub2, um.subjects);
 * 
 * // ===== In Danh Sách Ban Đầu =====
 * System.out.println(">> Danh sách ban đầu:");
 * um.printEntityList(um.students, "Sinh viên");
 * um.printEntityList(um.lecturers, "Giảng viên");
 * um.printEntityList(um.subjects, "Môn học");
 * 
 * // ===== Sửa đổi =====
 * System.out.println("\n>> Sửa đổi:");
 * um.editEntity("Nguyen Van A Updated", 1, um.students);
 * um.editEntity("Le Van C Updated", 101, um.lecturers);
 * um.editEntity("Toán Cao Cấp Updated", 201, um.subjects);
 * 
 * // ===== In Danh Sách Sau Sửa =====
 * System.out.println("\n>> Danh sách sau sửa đổi:");
 * um.printEntityList(um.students, "Sinh viên");
 * um.printEntityList(um.lecturers, "Giảng viên");
 * um.printEntityList(um.subjects, "Môn học");
 * 
 * // ===== Xoá =====
 * System.out.println("\n>> Xoá:");
 * um.deleteEntity(2, um.students);
 * um.deleteEntity(102, um.lecturers);
 * um.deleteEntity(202, um.subjects);
 * 
 * // ===== In Danh Sách Cuối Cùng =====
 * System.out.println("\n>> Danh sách cuối cùng:");
 * um.printEntityList(um.students, "Sinh viên");
 * um.printEntityList(um.lecturers, "Giảng viên");
 * um.printEntityList(um.subjects, "Môn học");
 * 
 * System.out.println("\n>>> Chạy ExamTest:");
 * TestExam.main(new String[] {});
 * 
 * System.out.println("\n>>> Chạy SimpleEnrollmentTest:");
 * SimpleEnrollmentTest.main(new String[] {});
 * }
 * }
 */