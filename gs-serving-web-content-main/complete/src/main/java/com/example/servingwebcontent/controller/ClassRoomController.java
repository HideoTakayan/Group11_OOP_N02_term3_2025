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
        try {
            classAiven ca = new classAiven();
            ArrayList<ClassRoom> classes = ca.getClassList();
            model.addAttribute("classes", classes);

            if (editId != null) {
                ClassRoom c = ca.getClassById(editId);
                model.addAttribute("editClass", c);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách lớp: " + e.getMessage());
        }
        return "classlist";
    }

    @GetMapping("/classlist/add")
    public String showAddClassForm() {  // ✅ Sửa lại tên hàm cho đúng mục đích
        return "addclass";
    }

    @PostMapping("/addclass")
    public String addClass(@RequestParam String classId, @RequestParam String className, Model model) {
        try {
            ClassRoom c = new ClassRoom(classId, className);
            classAiven ca = new classAiven();
            ca.insertClass(c);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm lớp: " + e.getMessage());
            return "addclass";
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
            } else {
                model.addAttribute("error", "Không tìm thấy lớp để cập nhật.");
                return "editclass";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật lớp: " + e.getMessage());
            return "editclass";
        }
        return "redirect:/classlist";
    }

    @GetMapping("/deleteclass")
    public String deleteClass(@RequestParam String classId, Model model) {
        try {
            classAiven ca = new classAiven();
            ca.deleteClass(classId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa lớp: " + e.getMessage());
        }
        return "redirect:/classlist";
    }
}
