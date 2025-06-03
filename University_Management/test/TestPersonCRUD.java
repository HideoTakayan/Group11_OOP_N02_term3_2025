import model.Student;
import model.Lecturer;
import bin.PersonService;

import java.time.LocalDate;

public class TestPersonCRUD {
    public static void main(String[] args) {
        PersonService service = new PersonService();

        // Thêm sinh viên và giảng viên
        Student student1 = new Student(101, "Nguyen Van A", LocalDate.of(2001, 3, 15), "Nam");
        Student student2 = new Student(102, "Tran Thi B", LocalDate.of(2002, 7, 22), "Nữ");
        Lecturer lecturer1 = new Lecturer("Le Van C", LocalDate.of(1975, 5, 5), "Nam", 201);
        Lecturer lecturer2 = new Lecturer("Pham Thi D", LocalDate.of(1980, 8, 30), "Nữ", 202);

        service.addPerson(student1);
        service.addPerson(student2);
        service.addPerson(lecturer1);
        service.addPerson(lecturer2);

        System.out.println("== Danh sách ban đầu ==");
        service.showAll();

        // Cập nhật tên sinh viên
        System.out.println("\n== Cập nhật tên sinh viên ID 101 ==");
        service.updateNameById(101, "Nguyen Van A Updated");
        service.showAll();

        // Xoá giảng viên
        System.out.println("\n== Xoá giảng viên ID 202 ==");
        service.deleteById(202);
        service.showAll();

        // Cập nhật tên giảng viên
        System.out.println("\n== Cập nhật tên giảng viên ID 201 ==");
        service.updateNameById(201, "Le Van C Updated");
        service.showAll();
    }
}
