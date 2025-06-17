package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.servingwebcontent.database.subjectAiven;
import com.example.servingwebcontent.model.Subject;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class SubjectController {

    @GetMapping("/subjectlist")
    public String subjectList(@RequestParam(required = false) String editId, Model model) {
        subjectAiven sa = new subjectAiven();
        ArrayList<Subject> subjects = sa.getSubjectList();
        model.addAttribute("subjects", subjects);

        if (editId != null) {
            Subject s = sa.getSubjectById(editId);
            model.addAttribute("editSubject", s);
        }

        return "subjectlist";
    }

    @GetMapping("/subjectlist/add")
    public String showAddSubjectForm() {
        return "addsubject";
    }

    @PostMapping("/addsubject")
    public String addSubject(@RequestParam String subjectName,
            @RequestParam int credits) {
        String subjectId = "sub" + new Random().nextInt(1000);
        Subject subject = new Subject(subjectId, subjectName, credits);
        new subjectAiven().insertSubject(subject);
        return "redirect:/subjectlist";
    }

    @GetMapping("/subjectlist/edit")
    public String editSubject(@RequestParam String subjectId, Model model) {
        subjectAiven sa = new subjectAiven();
        Subject subject = sa.getSubjectById(subjectId);
        model.addAttribute("subject", subject);

        return "editsubject";
    }

    @PostMapping("/updatesubject")
    public String updateSubject(@RequestParam String subjectId,
            @RequestParam String subjectName,
            @RequestParam int credits) {
        subjectAiven sa = new subjectAiven();
        Subject s = sa.getSubjectById(subjectId);
        if (s != null) {
            s.setSubjectName(subjectName);
            s.setCredits(credits);
            sa.updateSubject(s);
        }
        return "redirect:/subjectlist";
    }

    @GetMapping("/deletesubject")
    public String deleteSubject(@RequestParam String subjectId) {
        new subjectAiven().deleteSubject(subjectId);
        return "redirect:/subjectlist";
    }
}