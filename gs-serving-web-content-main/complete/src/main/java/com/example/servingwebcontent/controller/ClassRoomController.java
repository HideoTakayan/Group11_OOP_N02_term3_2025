package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.database.classAiven;
import com.example.servingwebcontent.model.ClassRoom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class ClassRoomController {

    @GetMapping("/classlist")
    public String classList(@RequestParam(required = false) String editId, Model model) {
        classAiven ca = new classAiven();
        ArrayList<ClassRoom> classes = ca.getClassList();
        model.addAttribute("classes", classes);

        if (editId != null) {
            ClassRoom c = ca.getClassById(editId);
            model.addAttribute("editClass", c);
        }

        return "classlist";
    }

    @GetMapping("/classlist/add")
    public String showAddStudentForm() {
        return "addclass";
    }

    @PostMapping("/addclass")
    public String addClass(@RequestParam String classId, @RequestParam String className) {
        ClassRoom c = new ClassRoom(classId, className);
        classAiven ca = new classAiven();
        ca.insertClass(c);
        return "redirect:/classlist";
    }

    @GetMapping("/classlist/edit")
    public String editClass(@RequestParam String classId, Model model) {
        classAiven ca = new classAiven();
        ClassRoom c = ca.getClassById(classId);
        model.addAttribute("classroom", c);
        return "editclass";
    }

    @PostMapping("/updateclass")
    public String updateClass(@RequestParam String classId, @RequestParam String className) {
        classAiven ca = new classAiven();
        ClassRoom c = ca.getClassById(classId);
        if (c != null) {
            c.setClassName(className);
            ca.updateClass(c);
        }
        return "redirect:/classlist";
    }

    @GetMapping("/deleteclass")
    public String deleteClass(@RequestParam String classId) {
        classAiven ca = new classAiven();
        ca.deleteClass(classId);
        return "redirect:/classlist";
    }
}
