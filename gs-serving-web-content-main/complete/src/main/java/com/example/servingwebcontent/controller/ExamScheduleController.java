package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.servingwebcontent.database.examScheduleAiven;
import com.example.servingwebcontent.model.ExamSchedule;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

@Controller
public class ExamScheduleController {

    @GetMapping("/examschedulelist")
    public String examScheduleList(@RequestParam(required = false) String editSubject, Model model) {
        examScheduleAiven esa = null;
        try {
            esa = new examScheduleAiven();
            ArrayList<ExamSchedule> examSchedules = esa.getExamSchedules();
            model.addAttribute("examSchedules", examSchedules);

            if (editSubject != null) {
                ExamSchedule editExam = esa.getExamBySubject(editSubject);
                model.addAttribute("editExam", editExam);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách lịch thi: " + e.getMessage());
        } finally {

        }

        return "examschedulelist";
    }

    @GetMapping("/examschedulelist/addschedule")
    public String showAddExamForm(Model model) {
        try {

        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị form thêm lịch thi: " + e.getMessage());
        }
        return "addschedule";
    }

    @PostMapping("/addexamschedule")
    public String addExamSchedule(@RequestParam String subject,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam String room,
            @RequestParam String building,
            Model model) {
        try {
            LocalTime localTime = LocalTime.parse(time);
            Time sqlTime = Time.valueOf(localTime.withSecond(0));
            ExamSchedule exam = new ExamSchedule(subject, Date.valueOf(date), sqlTime, room, building);
            examScheduleAiven esa = new examScheduleAiven();
            esa.insertExamSchedule(exam);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm lịch thi: " + e.getMessage());
            return "addschedule";
        } finally {

        }

        return "redirect:/examschedulelist";
    }

    @GetMapping("/examschedulelist/edit")
    public String editExamSchedule(@RequestParam String subject, Model model) {
        try {
            examScheduleAiven esa = new examScheduleAiven();
            ExamSchedule exam = esa.getExamBySubject(subject);
            model.addAttribute("exam", exam);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi mở form chỉnh sửa lịch thi: " + e.getMessage());
        } finally {

        }

        return "editschedule";
    }

    @PostMapping("/updateexamschedule")
    public String updateExamSchedule(@RequestParam String originalSubject,
            @RequestParam String subject,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam String room,
            @RequestParam String building,
            Model model) {
        try {
            ExamSchedule exam = new ExamSchedule(subject, Date.valueOf(date), Time.valueOf(time), room, building);
            examScheduleAiven esa = new examScheduleAiven();
            esa.updateExamSchedule(originalSubject, exam);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật lịch thi: " + e.getMessage());
            return "editschedule";
        } finally {

        }
        return "redirect:/examschedulelist";
    }

    @GetMapping("/deleteexamschedule")
    public String deleteExamSchedule(@RequestParam String subject, Model model) {
        try {
            examScheduleAiven esa = new examScheduleAiven();
            esa.deleteExamSchedule(subject);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa lịch thi: " + e.getMessage());
        } finally {
        }
        return "redirect:/examschedulelist";
    }
}
