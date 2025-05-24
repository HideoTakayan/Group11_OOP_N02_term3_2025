package University_Management.src.manager;

import University_Management.src.model.Student;
import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> addStudent(Student stu) {
        students.add(stu);
        return students;
    }

    public ArrayList<Student> editStudent(String name, int id) {
        for (Student s : students) {
            if (s.getStudentId() == id) {
                System.out.println("Tim thay sinh vien");
                s.setName(name);
            }
        }
        return students;
    }

    public ArrayList<Student> deleteStudent(int id) {
        students.removeIf(s -> s.getStudentId() == id);
        return students;
    }

    public void printStudentList() {
        System.out.println("=== Student List ===");
        for (Student s : students) {
            System.out.println("Student ID: " + s.getStudentId() + ", Ten: " + s.getName());
        }
    }
}
