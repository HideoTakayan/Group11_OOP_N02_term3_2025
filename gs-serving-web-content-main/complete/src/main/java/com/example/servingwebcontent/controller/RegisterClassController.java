package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.servingwebcontent.database.classSectionAiven;
import com.example.servingwebcontent.database.registerClassAiven;
import com.example.servingwebcontent.database.studentAiven;
import com.example.servingwebcontent.model.RegisterClassSection;

import java.util.List;
import java.util.UUID;

@Controller
public class RegisterClassController {

    @GetMapping("/registerclasslist")
    public String viewRegisterClassList(Model model) {
        List<RegisterClassSection> registerList = new registerClassAiven().getRegisterClassList();
        model.addAttribute("registerList", registerList);
        return "registerclasslist";
    }

    @GetMapping("/addregisterclass")
    public String showAddForm(Model model) {
        model.addAttribute("studentList", new studentAiven().getStudentList());
        model.addAttribute("classSectionList", new classSectionAiven().getAllClassSections());
        return "addregisterclass";
    }

    @PostMapping("/addregisterclass")
    public String addRegisterClass(@RequestParam String studentId,
            @RequestParam String classSectionId) {
        System.out.println("POST ADD: " + studentId + " - " + classSectionId);
        String registerId = UUID.randomUUID().toString();
        RegisterClassSection rc = new RegisterClassSection(registerId, studentId, classSectionId);
        new registerClassAiven().insertRegisterClass(rc);
        return "redirect:/registerclasslist";
    }

    @GetMapping("/registerclass/edit")
    public String editRegisterClass(@RequestParam("id") String registerId, Model model) {
        registerClassAiven dao = new registerClassAiven();
        RegisterClassSection rc = dao.getRegisterClassById(registerId);
        model.addAttribute("registerClass", rc);
        model.addAttribute("studentList", new studentAiven().getStudentList());
        model.addAttribute("classSectionList", new classSectionAiven().getAllClassSections());
        return "editregisterclass";
    }

    @PostMapping("/updateregisterclass")
    public String updateRegisterClass(@RequestParam String registerId,
            @RequestParam String studentId,
            @RequestParam String classSectionId) {
        RegisterClassSection rc = new RegisterClassSection(registerId, studentId, classSectionId);
        new registerClassAiven().updateRegisterClass(rc);
        return "redirect:/registerclasslist";
    }

    @GetMapping("/deleteregisterclass")
    public String deleteRegisterClass(@RequestParam("id") String registerId) {
        new registerClassAiven().deleteRegisterClass(registerId);
        return "redirect:/registerclasslist";
    }
}
