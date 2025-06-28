package com.example.servingwebcontent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.servingwebcontent.database.lecturerAiven;
import com.example.servingwebcontent.database.subjectAiven;
import com.example.servingwebcontent.model.Lecturer;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

@Controller
public class LecturerController {

    private final lecturerAiven lecturerDb = new lecturerAiven();
    private final subjectAiven subjectDb = new subjectAiven();
    private final Random random = new Random();

    private String generateLecturerId() {
        int number = random.nextInt(900) + 100; // 3 chữ số từ 100–999
        return "l" + number;
    }

@GetMapping("/lecturerlist")
public String lecturerList(@RequestParam(required = false) String editId,
                           @RequestParam(required = false) String keyword,
                           Model model) {
    try {
        ArrayList<Lecturer> lecturers;
        if (keyword != null && !keyword.trim().isEmpty()) {
            lecturers = lecturerDb.searchLecturersByName(keyword.trim());
            model.addAttribute("keyword", keyword);
        } else {
            lecturers = lecturerDb.getLecturerList();
        }

        model.addAttribute("lecturers", lecturers);

        if (editId != null) {
            Lecturer l = lecturerDb.getLecturerById(editId);
            model.addAttribute("editLecturer", l);
        }
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi tải danh sách giảng viên: " + e.getMessage());
    }
    return "lecturerlist";
}


    @GetMapping("/lecturerlist/add")
    public String showAddLecturerForm(Model model) {
        try {
            model.addAttribute("subjects", subjectDb.getSubjectList());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách môn học: " + e.getMessage());
        }
        return "addlecturer";
    }

    @PostMapping("/addlecturer")
    public String addLecturer(@RequestParam String name,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String department,
            @RequestParam String gender,
            @RequestParam String dateOfBirth,
            Model model) {
        try {
            String personId = "p" + UUID.randomUUID();
            String lecturerId = generateLecturerId();

            java.sql.Date dob = java.sql.Date.valueOf(dateOfBirth);
            Lecturer lecturer = new Lecturer(lecturerId, personId, name, address, email, dob, gender, department);
            lecturerDb.insertLecturer(lecturer);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm giảng viên: " + e.getMessage());
            return "addlecturer";
        }
        return "redirect:/lecturerlist";
    }

    @GetMapping("/lecturerlist/edit")
    public String editLecturer(@RequestParam String lecturerId, Model model) {
        try {
            Lecturer lecturer = lecturerDb.getLecturerById(lecturerId);
            model.addAttribute("lecturer", lecturer);
            model.addAttribute("subjects", subjectDb.getSubjectList());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị thông tin giảng viên: " + e.getMessage());
        }
        return "editlecturer";
    }

    @PostMapping("/updatelecturer")
    public String updateLecturer(@RequestParam String lecturerId,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String department,
            @RequestParam String gender,
            @RequestParam String dateOfBirth,
            @RequestParam String personId,
            Model model) {
        try {
            java.sql.Date dob = java.sql.Date.valueOf(dateOfBirth);
            Lecturer lecturer = new Lecturer(lecturerId, personId, name, address, email, dob, gender, department);
            lecturerDb.updateLecturer(lecturer);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật giảng viên: " + e.getMessage());
            return "editlecturer";
        }
        return "redirect:/lecturerlist";
    }

    @GetMapping("/deletelecturer")
    public String deleteLecturer(@RequestParam String lecturerId, Model model) {
        try {
            lecturerDb.deleteLecturer(lecturerId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xoá giảng viên: " + e.getMessage());
        }
        return "redirect:/lecturerlist";
    }
}
