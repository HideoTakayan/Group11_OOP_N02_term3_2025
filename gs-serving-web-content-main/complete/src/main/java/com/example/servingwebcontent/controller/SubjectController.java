package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.servingwebcontent.database.subjectAiven;
import com.example.servingwebcontent.model.Subject;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class SubjectController {

    @GetMapping("/subjectlist")
    public String subjectList(@RequestParam(required = false) String editId, Model model) {
        subjectAiven sa = null;
        try {
            sa = new subjectAiven();
            ArrayList<Subject> subjects = sa.getSubjectList();
            model.addAttribute("subjects", subjects);

            if (editId != null) {
                Subject s = sa.getSubjectById(editId);
                model.addAttribute("editSubject", s);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách môn học: " + e.getMessage());
        } finally {

        }

        return "subjectlist";
    }

    @GetMapping("/subjectlist/add")
    public String showAddSubjectForm(Model model) {
        try {

        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi mở form thêm môn học: " + e.getMessage());
        } finally {

        }

        return "addsubject";
    }

    @PostMapping("/addsubject")
    public String addSubject(@RequestParam String subjectName,
            @RequestParam int credits,
            Model model) {
        try {
            String subjectId = "sub" + new Random().nextInt(1000);
            Subject subject = new Subject(subjectId, subjectName, credits);
            new subjectAiven().insertSubject(subject);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm môn học: " + e.getMessage());
            return "addsubject";
        } finally {
        }

        return "redirect:/subjectlist";
    }

    @GetMapping("/subjectlist/edit")
    public String editSubject(@RequestParam String subjectId, Model model) {
        subjectAiven sa = null;
        try {
            sa = new subjectAiven();
            Subject subject = sa.getSubjectById(subjectId);
            model.addAttribute("subject", subject);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải thông tin môn học để chỉnh sửa: " + e.getMessage());
        } finally {
        }

        return "editsubject";
    }

    @PostMapping("/updatesubject")
    public String updateSubject(@RequestParam String subjectId,
            @RequestParam String subjectName,
            @RequestParam int credits,
            Model model) {
        subjectAiven sa = null;
        try {
            sa = new subjectAiven();
            Subject s = sa.getSubjectById(subjectId);
            if (s != null) {
                s.setSubjectName(subjectName);
                s.setCredits(credits);
                sa.updateSubject(s);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật môn học: " + e.getMessage());
            return "editsubject";
        } finally {
        }

        return "redirect:/subjectlist";
    }

    @GetMapping("/deletesubject")
    public String deleteSubject(@RequestParam String subjectId, Model model) {
        try {
            new subjectAiven().deleteSubject(subjectId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa môn học: " + e.getMessage());
        } finally {

        }

        return "redirect:/subjectlist";
    }
}
