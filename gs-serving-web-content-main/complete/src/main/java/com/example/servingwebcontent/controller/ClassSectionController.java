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
        classSectionAiven csa = null;
        subjectAiven sa = null;
        lectureAiven la = null;
        try {
            csa = new classSectionAiven();
            sa = new subjectAiven();
            la = new lectureAiven();

            List<ClassSection> list = csa.getAllClassSections();
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
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị danh sách lớp học: " + e.getMessage());
        } finally {
        }
        return "classsectionlist";
    }

    @GetMapping("/classsectionlist/add")
    public String showAddForm(Model model) {
        try {
            subjectAiven sa = new subjectAiven();
            lectureAiven la = new lectureAiven();
            model.addAttribute("subjects", sa.getSubjectList());
            model.addAttribute("lectures", la.getLectureList());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị form thêm lớp học: " + e.getMessage());
        } finally {
        }
        return "addclasssection";
    }

    @PostMapping("/addclasssection")
    public String addClassSection(
            @RequestParam String className,
            @RequestParam String subjectId,
            @RequestParam String lectureId,
            Model model) {
        try {
            String classId = "cls" + new Random().nextInt(1000);
            ClassSection cs = new ClassSection(classId, className, subjectId, lectureId);
            new classSectionAiven().insertClassSection(cs);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm lớp học: " + e.getMessage());
            return "addclasssection";
        } finally {
        }
        return "redirect:/classsectionlist";
    }

    @GetMapping("/classsectionlist/edit")
    public String editClassSection(@RequestParam String classId, Model model) {
        try {
            classSectionAiven csa = new classSectionAiven();
            ClassSection cs = csa.getClassSectionById(classId);
            model.addAttribute("classSection", cs);
            model.addAttribute("subjects", new subjectAiven().getSubjectList());
            model.addAttribute("lectures", new lectureAiven().getLectureList());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị form chỉnh sửa lớp học: " + e.getMessage());
        } finally {
        }
        return "editclasssection";
    }

    @PostMapping("/updateclasssection")
    public String updateClassSection(
            @RequestParam String classId,
            @RequestParam String className,
            @RequestParam String subjectId,
            @RequestParam String lectureId,
            Model model) {
        try {
            ClassSection cs = new ClassSection(classId, className, subjectId, lectureId);
            new classSectionAiven().updateClassSection(cs);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật lớp học: " + e.getMessage());
            return "editclasssection";
        } finally {
        }
        return "redirect:/classsectionlist";
    }

    @GetMapping("/deleteclasssection")
    public String deleteClassSection(@RequestParam String classId, Model model) {
        try {
            new classSectionAiven().deleteClassSection(classId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa lớp học: " + e.getMessage());
        } finally {
        }
        return "redirect:/classsectionlist";
    }
}
