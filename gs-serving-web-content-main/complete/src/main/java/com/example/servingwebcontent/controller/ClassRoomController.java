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
        classAiven ca = null;
        try {
            ca = new classAiven();
            ArrayList<ClassRoom> classes = ca.getClassList();
            model.addAttribute("classes", classes);

            if (editId != null) {
                ClassRoom c = ca.getClassById(editId);
                model.addAttribute("editClass", c);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách lớp: " + e.getMessage());
        } finally {
        }
        return "classlist";
    }

    @GetMapping("/classlist/add")
    public String showAddStudentForm() {
        return "addclass";
    }

    @PostMapping("/addclass")
    public String addClass(@RequestParam String classId, @RequestParam String className, Model model) {
        try {
            ClassRoom c = new ClassRoom(classId, className);
            new classAiven().insertClass(c);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm lớp: " + e.getMessage());
            return "addclass";
        } finally {
        }
        return "redirect:/classlist";
    }

    @GetMapping("/classlist/edit")
    public String editClass(@RequestParam String classId, Model model) {
        try {
            classAiven ca = new classAiven();
            ClassRoom c = ca.getClassById(classId);
            model.addAttribute("classroom", c);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải lớp để chỉnh sửa: " + e.getMessage());
        } finally {
        }
        return "editclass";
    }

    @PostMapping("/updateclass")
    public String updateClass(@RequestParam String classId, @RequestParam String className, Model model) {
        try {
            classAiven ca = new classAiven();
            ClassRoom c = ca.getClassById(classId);
            if (c != null) {
                c.setClassName(className);
                ca.updateClass(c);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật lớp: " + e.getMessage());
            return "editclass";
        } finally {
        }
        return "redirect:/classlist";
    }

    @GetMapping("/deleteclass")
    public String deleteClass(@RequestParam String classId, Model model) {
        try {
            new classAiven().deleteClass(classId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa lớp: " + e.getMessage());
        } finally {
        }
        return "redirect:/classlist";
    }
}
