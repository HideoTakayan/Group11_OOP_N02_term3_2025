package University_Management.src.bin;

import University_Management.src.model.Grade;

import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    public List<Grade> getGradesByStudentID(int studentID) {
        List<Grade> grades = new ArrayList<>();

        if (studentID == 1) {
            grades.add(new Grade(1, 101, 8.5));
            grades.add(new Grade(1, 102, 7.0));
            grades.add(new Grade(1, 103, 9.2));
        } else if (studentID == 2) {
            grades.add(new Grade(2, 101, 6.0));
            grades.add(new Grade(2, 102, 8.0));
        } else {
            System.out.println("Không tìm thấy điểm cho sinh viên có ID: " + studentID);
        }

        return grades;
    }
}
