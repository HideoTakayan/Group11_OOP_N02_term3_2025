package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.servingwebcontent.database.lectureAiven;
import com.example.servingwebcontent.database.subjectAiven;
import com.example.servingwebcontent.model.Lecture;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class LectureController {
    @GetMapping("/lecturelist")
    public String lectureList(@RequestParam(required = false) String editId, Model model) {
        lectureAiven la = new lectureAiven();
        ArrayList<Lecture> lectures = la.getLectureList();
        model.addAttribute("lectures", lectures);

        if (editId != null) {
            Lecture l = la.getLectureById(editId);
            model.addAttribute("editLecture", l);
        }
        return "lecturelist";
    }

    @PostMapping("/addlecture")
    public String addLecture(@RequestParam String name, @RequestParam String address,
            @RequestParam String email, @RequestParam String department) {
        Random rand = new Random();
        String personId = "p" + rand.nextInt(1000);
        String lectureId = "l" + rand.nextInt(1000);

        Lecture lecture = new Lecture(lectureId, personId, name, address, email, department);
        lectureAiven la = new lectureAiven();
        la.insertLecture(lecture);
        return "redirect:/lecturelist";
    }

    @GetMapping("/lecturelist/addlecture")
    public String showAddLectureForm(Model model) {
        subjectAiven sa = new subjectAiven();
        model.addAttribute("subjects", sa.getSubjectList());
        return "addlecture";
    }

    @GetMapping("/lecturelist/edit")
    public String editLecture(@RequestParam String lectureId, Model model) {
        lectureAiven la = new lectureAiven();
        Lecture lecture = la.getLectureById(lectureId);
        model.addAttribute("lecture", lecture);
        subjectAiven sa = new subjectAiven();
        model.addAttribute("subjects", sa.getSubjectList());

        return "editlecture";
    }

    @PostMapping("/updatelecture")
    public String updateLecture(@RequestParam String lectureId,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String department) {
        lectureAiven la = new lectureAiven();
        Lecture l = la.getLectureById(lectureId);
        if (l != null) {
            l.setName(name);
            l.setAddress(address);
            l.setEmail(email);
            l.setDepartment(department);
            la.updateLecture(l);
        }
        return "redirect:/lecturelist";
    }

    @GetMapping("/deletelecture")
    public String deleteLecture(@RequestParam String lectureId) {
        lectureAiven la = new lectureAiven();
        la.deleteLecture(lectureId);
        return "redirect:/lecturelist";
    }
}