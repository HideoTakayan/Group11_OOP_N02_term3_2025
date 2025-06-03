package University_Management.test;

import model.ExamSchedule;
import bin.UniversityManager;

public class TestExam {
    public static void main(String[] args) {
        UniversityManager manager = new UniversityManager();

        // Thêm lịch thi mẫu
        ExamSchedule exam1 = new ExamSchedule(101, "2025-06-15", "Ca sáng");
        ExamSchedule exam2 = new ExamSchedule(102, "2025-06-16", "Ca chiều");

        manager.addExamSchedule(exam1);
        manager.addExamSchedule(exam2);
        manager.showAllExamSchedules();
        manager.searchExamByDate("2025-06-15");
    }
}