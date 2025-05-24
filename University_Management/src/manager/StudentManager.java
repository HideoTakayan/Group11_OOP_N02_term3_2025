package University_Management.src.manager;

import java.util.ArrayList;
import java.util.List;

import University_Management.src.model.Student;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getStudentId() == id)
                return s;
        }
        return null;
    }

    public boolean updateStudent(int id, String name, String gender, String dob) {
        Student s = findStudentById(id);
        if (s == null)
            return false;
        students.remove(s);
        students.add(new Student(id, name, gender, dob));
        return true;
    }

    public boolean deleteStudent(int id) {
        Student s = findStudentById(id);
        if (s == null)
            return false;
        students.remove(s);
        return true;
    }
}
