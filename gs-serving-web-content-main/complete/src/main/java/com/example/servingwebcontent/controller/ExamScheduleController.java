package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.servingwebcontent.database.examScheduleAiven;
import com.example.servingwebcontent.model.ExamSchedule;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.time.LocalTime;

@Controller
public class ExamScheduleController {

    @GetMapping("/examschedulelist")
    public String examScheduleList(@RequestParam(required = false) String editSubject, Model model) {
        examScheduleAiven esa = new examScheduleAiven();
        ArrayList<ExamSchedule> examSchedules = esa.getExamSchedules();
        model.addAttribute("examSchedules", examSchedules);

        if (editSubject != null) {
            ExamSchedule editExam = esa.getExamBySubject(editSubject);
            model.addAttribute("editExam", editExam);
        }

        return "examschedulelist";
    }

    @GetMapping("/examschedulelist/addschedule")
    public String showAddExamForm() {
        return "addschedule";
    }

    @PostMapping("/addexamschedule")
    public String addExamSchedule(@RequestParam String subject,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam String room,
            @RequestParam String building) {
        LocalTime localTime = LocalTime.parse(time);
        Time sqlTime = Time.valueOf(localTime.withSecond(0));
        ExamSchedule exam = new ExamSchedule(subject, Date.valueOf(date), sqlTime, room, building);
        examScheduleAiven esa = new examScheduleAiven();
        esa.insertExamSchedule(exam);

        return "redirect:/examschedulelist";
    }

    @GetMapping("/examschedulelist/edit")
    public String editExamSchedule(@RequestParam String subject, Model model) {
        examScheduleAiven esa = new examScheduleAiven();
        ExamSchedule exam = esa.getExamBySubject(subject);
        model.addAttribute("exam", exam);
        return "editschedule";
    }

    @PostMapping("/updateexamschedule")
    public String updateExamSchedule(@RequestParam String originalSubject,
            @RequestParam String subject,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam String room,
            @RequestParam String building) {

        ExamSchedule exam = new ExamSchedule(subject, Date.valueOf(date), Time.valueOf(time), room, building);
        examScheduleAiven esa = new examScheduleAiven();
        esa.updateExamSchedule(originalSubject, exam);

        return "redirect:/examschedulelist";
    }

    @GetMapping("/deleteexamschedule")
    public String deleteExamSchedule(@RequestParam String subject) {
        examScheduleAiven esa = new examScheduleAiven();
        esa.deleteExamSchedule(subject);
        return "redirect:/examschedulelist";
    }
}
