package University_Management.src.manager;

import University_Management.src.model.Lecturers;
import java.util.ArrayList;
import java.util.List;

public class LecturerManager {
    private List<Lecturers> lecturers = new ArrayList<>();

    public void addLecturer(Lecturers l) {
        lecturers.add(l);
    }

    public List<Lecturers> getAllLecturers() {
        return lecturers;
    }

    public Lecturers findLecturerById(int id) {
        for (Lecturers l : lecturers) {
            if (l.getLecturerId() == id)
                return l;
        }
        return null;
    }

    public boolean updateLecturer(int id, String name, String gender, String dob) {
        Lecturers l = findLecturerById(id);
        if (l == null)
            return false;

        l.setName(name);
        l.setGender(gender);
        l.setDateOfBirth(dob);
        return true;
    }

    public boolean deleteLecturer(int id) {
        Lecturers l = findLecturerById(id);
        if (l == null)
            return false;
        lecturers.remove(l);
        return true;
    }
}
