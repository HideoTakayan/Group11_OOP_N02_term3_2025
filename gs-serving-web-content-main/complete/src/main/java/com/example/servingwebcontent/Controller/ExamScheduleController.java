package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.examScheduleAiven;
import com.example.servingwebcontent.model.ExamSchedule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

@Controller
public class ExamScheduleController {

    private final examScheduleAiven examScheduleDao = new examScheduleAiven();

    @GetMapping("/examschedulelist")
    public String examScheduleList(@RequestParam(required = false) String keyword,
                               @RequestParam(required = false) String editSubjectName,
                               Model model) {
    try {
        ArrayList<ExamSchedule> examSchedules;

        if (keyword != null && !keyword.trim().isEmpty()) {
            examSchedules = examScheduleDao.searchExamSchedules(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            examSchedules = examScheduleDao.getExamSchedules();
        }

        model.addAttribute("examSchedules", examSchedules);

        if (editSubjectName != null) {
            ExamSchedule editExam = examScheduleDao.getExamBySubject(editSubjectName);
            if (editExam != null) {
                model.addAttribute("editExam", editExam);
            }
        }

    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi tải danh sách lịch thi: " + e.getMessage());
    }
    return "examschedulelist";
}


    @GetMapping("/examschedulelist/addschedule")
    public String showAddExamForm() {
        return "addschedule";
    }

    @PostMapping("/addexamschedule")
    public String addExamSchedule(
            @RequestParam String subjectName,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam int durationMinutes,
            @RequestParam String examFormat,
            @RequestParam String location,
            Model model) {
        try {
            LocalTime localTime = LocalTime.parse(time);
            Time sqlTime = Time.valueOf(localTime.withSecond(0));
            Date sqlDate = Date.valueOf(date);

            ExamSchedule exam = new ExamSchedule(subjectName, sqlDate, sqlTime, durationMinutes, examFormat, location);
            examScheduleDao.insertExamSchedule(exam);
            return "redirect:/examschedulelist";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Định dạng ngày hoặc giờ không hợp lệ!");
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm lịch thi: " + e.getMessage());
        }
        return "addschedule";
    }

    @GetMapping("/examschedulelist/edit")
    public String editExamSchedule(@RequestParam String subjectName, Model model) {
        try {
            ExamSchedule exam = examScheduleDao.getExamBySubject(subjectName);
            if (exam != null) {
                model.addAttribute("exam", exam);
            } else {
                model.addAttribute("error", "Không tìm thấy lịch thi cho môn học: " + subjectName);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi mở form chỉnh sửa lịch thi: " + e.getMessage());
        }
        return "editschedule";
    }

    @PostMapping("/updateexamschedule")
    public String updateExamSchedule(
            @RequestParam String originalSubjectName,
            @RequestParam String subjectName,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam int durationMinutes,
            @RequestParam String examFormat,
            @RequestParam String location,
            Model model) {
        try {
            LocalTime localTime = LocalTime.parse(time);
            Time sqlTime = Time.valueOf(localTime.withSecond(0));
            Date sqlDate = Date.valueOf(date);

            ExamSchedule exam = new ExamSchedule(subjectName, sqlDate, sqlTime, durationMinutes, examFormat, location);
            examScheduleDao.updateExamSchedule(originalSubjectName, exam);
            return "redirect:/examschedulelist";

        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật lịch thi: " + e.getMessage());
            return "editschedule";
        }
    }

    @GetMapping("/deleteexamschedule")
    public String deleteExamSchedule(@RequestParam String subjectName, Model model) {
        try {
            examScheduleDao.deleteExamSchedule(subjectName);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa lịch thi: " + e.getMessage());
        }
        return "redirect:/examschedulelist";
    }
}
