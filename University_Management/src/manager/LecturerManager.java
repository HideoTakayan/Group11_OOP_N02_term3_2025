package University_Management.src.manager;

import University_Management.src.model.Lecturers;
import java.util.ArrayList;

public class LecturerManager {
    private ArrayList<Lecturers> lecturers = new ArrayList<>();

    public ArrayList<Lecturers> addLecturer(Lecturers lec) {
        lecturers.add(lec);
        return lecturers;
    }

    public ArrayList<Lecturers> editLecturer(String name, int id) {
        for (Lecturers l : lecturers) {
            if (l.getLecturerId() == id) {
                System.out.println("Tim thay giang vien");
                l.setName(name);
            }
        }
        return lecturers;
    }

    public ArrayList<Lecturers> deleteLecturer(int id) {
        lecturers.removeIf(l -> l.getLecturerId() == id);
        return lecturers;
    }

    public void printLecturerList() {
        System.out.println("=== Lecturer List ===");
        for (Lecturers l : lecturers) {
            System.out.println("Lecturer ID: " + l.getLecturerId() + ", Ten: " + l.getName());
        }
    }
}
