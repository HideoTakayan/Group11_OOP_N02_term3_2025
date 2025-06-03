package University_Management.test;

import University_Management.src.model.Lecturer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LecturerTest {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dob = LocalDate.parse("15/03/1975", formatter);

        // Tạo đối tượng Lecturer với LocalDate đúng kiểu
        Lecturer lec1 = new Lecturer(101, "Tran Van B", dob, "Nam");

        System.out.println("=== Thông tin giảng viên ban đầu ===");
        System.out.println("ID: " + lec1.getLecturerID());
        System.out.println("Tên: " + lec1.getName());
        System.out.println("Giới tính: " + lec1.getGender());
        System.out.println("Ngày sinh: " + lec1.getDateOfBirth());
    }
}
