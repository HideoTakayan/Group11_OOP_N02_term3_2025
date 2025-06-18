package com.example.servingwebcontent.controller;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.servingwebcontent.database.environmentAiven;
import com.example.servingwebcontent.model.Environment;

import java.util.ArrayList;
import java.util.Random;

@Controller
@Primary
public class EnvironmentControllers {

    @GetMapping("/environmentlist")
    public String environmentList(@RequestParam(required = false) String editId, Model model) {
        environmentAiven ea = null;
        try {
            ea = new environmentAiven();
            ArrayList<Environment> environments = ea.getEnvironments();
            model.addAttribute("environments", environments);

            if (editId != null) {
                Environment env = ea.getEnvironmentById(editId);
                model.addAttribute("editEnvironment", env);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị danh sách phòng học: " + e.getMessage());
        } finally {
        }
        return "environmentlist";
    }

    @PostMapping("/addenvironment")
    public String addEnvironment(
            @RequestParam String roomNumber,
            @RequestParam String building,
            @RequestParam String subject,
            @RequestParam String lectureId,
            @RequestParam int studentCount,
            @RequestParam String dayOfWeek,
            @RequestParam String time,
            Model model) {
        try {
            String envId = "env" + new Random().nextInt(1000);
            Environment env = new Environment(envId, roomNumber, building, subject, lectureId, studentCount, dayOfWeek,
                    time);
            new environmentAiven().insertEnvironment(env);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm phòng học: " + e.getMessage());
            return "addenvironment";
        } finally {
        }
        return "redirect:/environmentlist";
    }

    @GetMapping("/addenvironment")
    public String showAddEnvironmentForm() {
        return "addenvironment";
    }

    @GetMapping("/environmentlist/edit")
    public String editEnvironment(@RequestParam String envId, Model model) {
        try {
            environmentAiven ea = new environmentAiven();
            Environment env = ea.getEnvironmentById(envId);
            model.addAttribute("env", env);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị thông tin phòng học để chỉnh sửa: " + e.getMessage());
        } finally {
        }
        return "editenvironment";
    }

    @PostMapping("/updateenvironment")
    public String updateEnvironment(
            @RequestParam String envId,
            @RequestParam String roomNumber,
            @RequestParam String building,
            @RequestParam String subject,
            @RequestParam String lectureId,
            @RequestParam int studentCount,
            @RequestParam String dayOfWeek,
            @RequestParam String time,
            Model model) {
        try {
            Environment env = new Environment(envId, roomNumber, building, subject, lectureId, studentCount, dayOfWeek,
                    time);
            new environmentAiven().updateEnvironment(env);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật phòng học: " + e.getMessage());
            return "editenvironment";
        } finally {
        }
        return "redirect:/environmentlist";
    }

    @GetMapping("/deleteenvironment")
    public String deleteEnvironment(@RequestParam String envId, Model model) {
        try {
            new environmentAiven().deleteEnvironment(envId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa phòng học: " + e.getMessage());
        } finally {
        }
        return "redirect:/environmentlist";
    }
}
