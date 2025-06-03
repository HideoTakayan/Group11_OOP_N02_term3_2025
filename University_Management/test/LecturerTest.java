package University_Management.test;

import University_Management.src.model.Lecturer;

import java.time.LocalDate;
import java.util.*;

public class LecturerTest {
    public static void main(String[] args) {
        // Tao cac doi tuong Lecturer
        Lecturer l1 = new Lecturer("Dr. An", LocalDate.of(1980, 4, 12), "Male", 2001);
        Lecturer l2 = new Lecturer("Prof. Binh", LocalDate.of(1975, 1, 30), "Male", 2003);
        Lecturer l3 = new Lecturer("Ms. Chi", LocalDate.of(1985, 9, 25), "Female", 2002);

        // Them vao danh sach
        List<Lecturer> lecturers = new ArrayList<>();
        lecturers.add(l1);
        lecturers.add(l2);
        lecturers.add(l3);

        // In danh sach ban dau
        System.out.println("Danh sach giang vien ban dau:");
        for (Lecturer l : lecturers) {
            System.out.println(l);
        }

        // Sap xep theo ten giang vien
        lecturers.sort(Comparator.comparing(Lecturer::getName));

        // In danh sach sau khi sap xep
        System.out.println("\nDanh sach giang vien sau khi sap xep theo ten:");
        for (Lecturer l : lecturers) {
            System.out.println(l);
        }
    }
}
