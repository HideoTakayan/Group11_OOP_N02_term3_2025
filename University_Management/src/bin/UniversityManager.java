package University_Management.src.bin;

import University_Management.src.model.Person;
import University_Management.src.model.Student;
import University_Management.src.model.Lecturers;
import University_Management.src.model.Subject;

import java.util.ArrayList;

public class UniversityManager {
    public ArrayList<Student> students = new ArrayList<>();
    public ArrayList<Lecturers> lecturers = new ArrayList<>();
    public ArrayList<Subject> subjects = new ArrayList<>();

    public <T extends Person> ArrayList<T> addEntity(T obj, ArrayList<T> list) {
        list.add(obj);
        return list;
    }

    public <T extends Person> ArrayList<T> editEntity(String name, int id, ArrayList<T> list) {
        for (T entity : list) {
            if (entity.getId() == id) {
                entity.setName(name);
                System.out.println("Đã cập nhật: " + name);
            }
        }
        return list;
    }

    public <T extends Person> ArrayList<T> deleteEntity(int id, ArrayList<T> list) {
        list.removeIf(entity -> entity.getId() == id);
        return list;
    }

    public <T extends Person> void printEntityList(ArrayList<T> list, String title) {
        System.out.println("=== " + title + " ===");
        for (T entity : list) {
            System.out.println("ID: " + entity.getId() + ", Tên: " + entity.getName());
        }
    }
}
