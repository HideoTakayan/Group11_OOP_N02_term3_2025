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
        environmentAiven ea = new environmentAiven();
        ArrayList<Environment> environments = ea.getEnvironments();
        model.addAttribute("environments", environments);

        if (editId != null) {
            Environment env = ea.getEnvironmentById(editId);
            model.addAttribute("editEnvironment", env);
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
            @RequestParam String time) {
        String envId = "env" + new Random().nextInt(1000);
        Environment env = new Environment(envId, roomNumber, building, subject, lectureId, studentCount, dayOfWeek,
                time);
        new environmentAiven().insertEnvironment(env);
        return "redirect:/environmentlist";
    }

    @GetMapping("/environmentlist/edit")
    public String editEnvironment(@RequestParam String envId, Model model) {
        environmentAiven ea = new environmentAiven();
        Environment env = ea.getEnvironmentById(envId);
        model.addAttribute("env", env);
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
            @RequestParam String time) {
        Environment env = new Environment(envId, roomNumber, building, subject, lectureId, studentCount, dayOfWeek,
                time);
        new environmentAiven().updateEnvironment(env);
        return "redirect:/environmentlist";
    }

    @GetMapping("/deleteenvironment")
    public String deleteEnvironment(@RequestParam String envId) {
        new environmentAiven().deleteEnvironment(envId);
        return "redirect:/environmentlist";
    }
}