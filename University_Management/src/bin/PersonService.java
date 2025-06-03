package bin;

import model.Person;
import model.Student;
import model.Lecturer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonService {
    private List<Person> personList = new ArrayList<>();

    // Create
    public void addPerson(Person person) {
        personList.add(person);
    }

    // Read
    public void showAll() {
        for (Person person : personList) {
            System.out.println(person.toString());
        }
    }

    // Update (theo ID cho Student/Lecturer)
    public void updateNameById(int id, String newName) {
        for (Person person : personList) {
            if (person instanceof Student student && student.getStudentID() == id) {
                student.setName(newName);
                return;
            } else if (person instanceof Lecturer lecturer && lecturer.getLecturerID() == id) {
                lecturer.setName(newName);
                return;
            }
        }
    }

    // Delete (theo ID)
    public void deleteById(int id) {
        personList.removeIf(person -> (person instanceof Student student && student.getStudentID() == id)
                || (person instanceof Lecturer lecturer && lecturer.getLecturerID() == id));
    }
}
