package com.example.servingwebcontent.controller;

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

    @GetMapping("/examschedulelist")
    public String examScheduleList(@RequestParam(required = false) String editSubject, Model model) {
        try {
            examScheduleAiven esa = new examScheduleAiven();
            ArrayList<ExamSchedule> examSchedules = esa.getExamSchedules();
            model.addAttribute("examSchedules", examSchedules);

            if (editSubject != null) {
                ExamSchedule editExam = esa.getExamBySubject(editSubject);
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
            @RequestParam String subject,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam String room,
            @RequestParam String building,
            Model model) {
        try {
            LocalTime localTime = LocalTime.parse(time); // Phải là định dạng "HH:mm"
            Time sqlTime = Time.valueOf(localTime.withSecond(0));
            Date sqlDate = Date.valueOf(date); // Phải là "yyyy-MM-dd"

            ExamSchedule exam = new ExamSchedule(subject, sqlDate, sqlTime, room, building);
            new examScheduleAiven().insertExamSchedule(exam);
            return "redirect:/examschedulelist";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Định dạng ngày hoặc giờ không hợp lệ!");
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm lịch thi: " + e.getMessage());
        }
        return "addschedule";
    }

    @GetMapping("/examschedulelist/edit")
    public String editExamSchedule(@RequestParam String subject, Model model) {
        try {
            ExamSchedule exam = new examScheduleAiven().getExamBySubject(subject);
            if (exam != null) {
                model.addAttribute("exam", exam);
            } else {
                model.addAttribute("error", "Không tìm thấy lịch thi cho môn học: " + subject);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi mở form chỉnh sửa lịch thi: " + e.getMessage());
        }
        return "editschedule";
    }

    @PostMapping("/updateexamschedule")
    public String updateExamSchedule(
            @RequestParam String originalSubject,
            @RequestParam String subject,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam String room,
            @RequestParam String building,
            Model model) {
        try {
            LocalTime localTime = LocalTime.parse(time);
            Time sqlTime = Time.valueOf(localTime.withSecond(0));
            Date sqlDate = Date.valueOf(date);

            ExamSchedule exam = new ExamSchedule(subject, sqlDate, sqlTime, room, building);
            new examScheduleAiven().updateExamSchedule(originalSubject, exam);
            return "redirect:/examschedulelist";

        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật lịch thi: " + e.getMessage());
            return "editschedule";
        }
    }

    @GetMapping("/deleteexamschedule")
    public String deleteExamSchedule(@RequestParam String subject, Model model) {
        try {
            new examScheduleAiven().deleteExamSchedule(subject);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa lịch thi: " + e.getMessage());
        }
        return "redirect:/examschedulelist";
    }
}
