package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.classSectionAiven;
import com.example.servingwebcontent.database.subjectAiven;
import com.example.servingwebcontent.model.ClassSection;
import com.example.servingwebcontent.model.Lecturer;
import com.example.servingwebcontent.model.Subject;
import com.example.servingwebcontent.database.lecturerAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ClassSectionController {

    @GetMapping("/classsectionlist")
    public String listClassSections(@RequestParam(required = false) String editId, Model model) {
        try {
            classSectionAiven csa = new classSectionAiven();
            List<ClassSection> list = csa.getAllClassSections();
            model.addAttribute("classSections", list);

            if (editId != null) {
                ClassSection editCs = csa.getClassSectionById(editId);
                model.addAttribute("editClassSection", editCs);
            }

        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị danh sách lớp học: " + e.getMessage());
        }

        return "classsectionlist";
    }

    @GetMapping("/classsectionlist/add")
    public String showAddForm(Model model) {
        try {
            subjectAiven sa = new subjectAiven();
            lecturerAiven la = new lecturerAiven();
            model.addAttribute("subjects", sa.getSubjectList());
            model.addAttribute("lecturers", la.getLecturerList());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị form thêm lớp học: " + e.getMessage());
        }

        return "addclasssection";
    }

    @PostMapping("/addclasssection")
    public String addClassSection(
            @RequestParam String className,
            @RequestParam String subjectId,
            @RequestParam String lecturerId,
            Model model) {
        try {
            String classId = "cls" + new Random().nextInt(1000);

            ClassSection cs = new ClassSection();
            cs.setClassId(classId);
            cs.setClassName(className);
            cs.setSubjectId(subjectId);
            cs.setLecturerId(lecturerId);

            classSectionAiven csa = new classSectionAiven();
            csa.insertClassSection(cs);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm lớp học: " + e.getMessage());
            return "addclasssection";
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
            model.addAttribute("lecturers", new lecturerAiven().getLecturerList());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị form chỉnh sửa lớp học: " + e.getMessage());
        }

        return "editclasssection";
    }

    @PostMapping("/updateclasssection")
    public String updateClassSection(
            @RequestParam String classId,
            @RequestParam String className,
            @RequestParam String subjectId,
            @RequestParam String lecturerId,
            Model model) {
        try {
            ClassSection cs = new ClassSection();
            cs.setClassId(classId);
            cs.setClassName(className);
            cs.setSubjectId(subjectId);
            cs.setLecturerId(lecturerId);

            classSectionAiven csa = new classSectionAiven();
            csa.updateClassSection(cs);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật lớp học: " + e.getMessage());
            return "editclasssection";
        }

        return "redirect:/classsectionlist";
    }

    @GetMapping("/deleteclasssection")
    public String deleteClassSection(@RequestParam String classId, Model model) {
        try {
            classSectionAiven csa = new classSectionAiven();
            csa.deleteClassSection(classId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa lớp học: " + e.getMessage());
        }

        return "redirect:/classsectionlist";
    }
}
