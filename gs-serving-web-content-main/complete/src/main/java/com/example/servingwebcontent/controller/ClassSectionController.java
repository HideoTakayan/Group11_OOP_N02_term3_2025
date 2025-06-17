package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.database.classSectionAiven;
import com.example.servingwebcontent.database.subjectAiven;
import com.example.servingwebcontent.model.ClassSection;
import com.example.servingwebcontent.model.Lecture;
import com.example.servingwebcontent.model.Subject;
import com.example.servingwebcontent.database.lectureAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ClassSectionController {

    @GetMapping("/classsectionlist")
    public String listClassSections(@RequestParam(required = false) String editId, Model model) {
        classSectionAiven csa = new classSectionAiven();
        subjectAiven sa = new subjectAiven();
        lectureAiven la = new lectureAiven();

        List<ClassSection> list = csa.getAllClassSections();

        // Gán subjectName và lectureName
        for (ClassSection cs : list) {
            Subject subject = sa.getSubjectById(cs.getSubjectId());
            Lecture lecture = la.getLectureById(cs.getLectureId());

            if (subject != null)
                cs.setSubjectName(subject.getSubjectName());
            if (lecture != null)
                cs.setLectureName(lecture.getName());
        }

        model.addAttribute("classSections", list);

        if (editId != null) {
            model.addAttribute("editClassSection", csa.getClassSectionById(editId));
        }

        return "classsectionlist";
    }

    @GetMapping("/classsectionlist/add")
    public String showAddForm(Model model) {
        subjectAiven sa = new subjectAiven();
        lectureAiven la = new lectureAiven();
        model.addAttribute("subjects", sa.getSubjectList());
        model.addAttribute("lectures", la.getLectureList());
        return "addclasssection";
    }

    @PostMapping("/addclasssection")
    public String addClassSection(
            @RequestParam String className,
            @RequestParam String subjectId,
            @RequestParam String lectureId) {

        String classId = "cls" + new Random().nextInt(1000);
        ClassSection cs = new ClassSection(classId, className, subjectId, lectureId);
        new classSectionAiven().insertClassSection(cs);
        return "redirect:/classsectionlist";
    }

    @GetMapping("/classsectionlist/edit")
    public String editClassSection(@RequestParam String classId, Model model) {
        classSectionAiven csa = new classSectionAiven();
        ClassSection cs = csa.getClassSectionById(classId);
        model.addAttribute("classSection", cs);
        model.addAttribute("subjects", new subjectAiven().getSubjectList());
        model.addAttribute("lectures", new lectureAiven().getLectureList());
        return "editclasssection";
    }

    @PostMapping("/updateclasssection")
    public String updateClassSection(
            @RequestParam String classId,
            @RequestParam String className,
            @RequestParam String subjectId,
            @RequestParam String lectureId) {
        ClassSection cs = new ClassSection(classId, className, subjectId, lectureId);
        new classSectionAiven().updateClassSection(cs);
        return "redirect:/classsectionlist";
    }

    @GetMapping("/deleteclasssection")
    public String deleteClassSection(@RequestParam String classId) {
        new classSectionAiven().deleteClassSection(classId);
        return "redirect:/classsectionlist";
    }
}
