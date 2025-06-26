package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.environmentAiven;
import com.example.servingwebcontent.model.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class EnvironmentController {

    @GetMapping("/environmentlist")
    public String environmentList(Model model) {
        try {
            environmentAiven ea = new environmentAiven();
            ArrayList<Environment> environments = ea.getEnvironments();

            // Gom nhóm theo tên môn học
            Map<String, List<Environment>> groupedEnvironments = environments.stream()
                    .collect(Collectors.groupingBy(Environment::getSubjectName, LinkedHashMap::new, Collectors.toList()));

            model.addAttribute("groupedEnvironments", groupedEnvironments);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị danh sách lịch học: " + e.getMessage());
        }
        return "environmentlist";
    }

    @GetMapping("/addenvironment")
    public String showAddEnvironmentForm() {
        return "addenvironment";
    }

    @PostMapping("/addenvironment")
    public String addEnvironment(
            @RequestParam String classId,
            @RequestParam String className,
            @RequestParam String subjectName,
            @RequestParam String lecturerName,
            @RequestParam String location,
            @RequestParam String dayOfWeek,
            @RequestParam String time,
            Model model) {
        try {
            String enviromentId = "env" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
            Environment env = new Environment(enviromentId, classId, className, subjectName, lecturerName, location,
                    dayOfWeek, time);
            new environmentAiven().insertEnvironment(env);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm lịch học: " + e.getMessage());
            return "addenvironment";
        }
        return "redirect:/environmentlist";
    }

    @GetMapping("/environmentlist/edit")
    public String editEnvironment(@RequestParam String enviromentId, Model model) {
        try {
            environmentAiven ea = new environmentAiven();
            Environment env = ea.getEnvironmentById(enviromentId);
            if (env == null) {
                model.addAttribute("error", "Không tìm thấy lịch học với ID: " + enviromentId);
                return "redirect:/environmentlist";
            }
            model.addAttribute("env", env);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị thông tin lịch học: " + e.getMessage());
        }
        return "editenvironment";
    }

    @PostMapping("/updateenvironment")
    public String updateEnvironment(
            @RequestParam String enviromentId,
            @RequestParam String classId,
            @RequestParam String className,
            @RequestParam String subjectName,
            @RequestParam String lecturerName,
            @RequestParam String location,
            @RequestParam String dayOfWeek,
            @RequestParam String time,
            Model model) {
        try {
            Environment env = new Environment(enviromentId, classId, className, subjectName, lecturerName, location,
                    dayOfWeek, time);
            new environmentAiven().updateEnvironment(env);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật lịch học: " + e.getMessage());
            return "editenvironment";
        }
        return "redirect:/environmentlist";
    }

    @GetMapping("/deleteenvironment")
    public String deleteEnvironment(@RequestParam String enviromentId, Model model) {
        try {
            new environmentAiven().deleteEnvironment(enviromentId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa lịch học: " + e.getMessage());
        }
        return "redirect:/environmentlist";
    }
}
