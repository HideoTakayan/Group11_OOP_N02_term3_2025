package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.servingwebcontent.database.lectureAiven;
import com.example.servingwebcontent.database.subjectAiven;
import com.example.servingwebcontent.model.Lecture;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class LectureController {

    @GetMapping("/lecturelist")
    public String lectureList(@RequestParam(required = false) String editId, Model model) {
        try {
            lectureAiven la = new lectureAiven();
            ArrayList<Lecture> lectures = la.getLectureList();
            model.addAttribute("lectures", lectures);

            if (editId != null) {
                Lecture l = la.getLectureById(editId);
                model.addAttribute("editLecture", l);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách giảng viên: " + e.getMessage());
        }
        return "lecturelist";
    }

    @GetMapping("/lecturelist/addlecture")
    public String showAddLectureForm(Model model) {
        try {
            subjectAiven sa = new subjectAiven();
            model.addAttribute("subjects", sa.getSubjectList());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách môn học: " + e.getMessage());
        }
        return "addlecture";
    }

    @PostMapping("/addlecture")
    public String addLecture(@RequestParam String name,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String department,
            Model model) {
        try {
            Random rand = new Random();
            String personId = "p" + rand.nextInt(1000);
            String lectureId = "l" + rand.nextInt(1000);

            Lecture lecture = new Lecture(lectureId, personId, name, address, email, department);
            new lectureAiven().insertLecture(lecture);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm giảng viên: " + e.getMessage());
            return "addlecture";
        }
        return "redirect:/lecturelist";
    }

    @GetMapping("/lecturelist/edit")
    public String editLecture(@RequestParam String lectureId, Model model) {
        try {
            lectureAiven la = new lectureAiven();
            Lecture lecture = la.getLectureById(lectureId);
            model.addAttribute("lecture", lecture);

            subjectAiven sa = new subjectAiven();
            model.addAttribute("subjects", sa.getSubjectList());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị thông tin giảng viên: " + e.getMessage());
        }
        return "editlecture";
    }

    @PostMapping("/updatelecture")
    public String updateLecture(@RequestParam String lectureId,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String department,
            Model model) {
        try {
            lectureAiven la = new lectureAiven();
            Lecture l = la.getLectureById(lectureId);
            if (l != null) {
                l.setName(name);
                l.setAddress(address);
                l.setEmail(email);
                l.setDepartment(department);
                la.updateLecture(l);
            } else {
                model.addAttribute("error", "Không tìm thấy giảng viên cần cập nhật.");
                return "editlecture";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật giảng viên: " + e.getMessage());
            return "editlecture";
        }
        return "redirect:/lecturelist";
    }

    @GetMapping("/deletelecture")
    public String deleteLecture(@RequestParam String lectureId, Model model) {
        try {
            new lectureAiven().deleteLecture(lectureId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xoá giảng viên: " + e.getMessage());
        }
        return "redirect:/lecturelist";
    }
}
