package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.classAiven;
import com.example.servingwebcontent.model.StudentClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class ClassController {

    private final classAiven dao = new classAiven(); // ✅ Đổi ClassDAO -> classAiven

    @GetMapping("/classlist")
    public String classList(@RequestParam(required = false) String editId, Model model) {
        try {
            ArrayList<StudentClass> classes = dao.getClassList();
            model.addAttribute("classes", classes);

            if (editId != null) {
                StudentClass editClass = dao.getClassById(editId);
                model.addAttribute("editClass", editClass);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách lớp: " + e.getMessage());
        }
        return "classlist";
    }

    @GetMapping("/classlist/add")
    public String showAddClassForm() {
        return "addclass";
    }

    @PostMapping("/addclass")
    public String addClass(@RequestParam String classId, @RequestParam String className, Model model) {
        try {
            StudentClass c = new StudentClass(classId, className);
            dao.insertClass(c);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm lớp: " + e.getMessage());
            return "addclass";
        }
        return "redirect:/classlist";
    }

    @GetMapping("/classlist/edit")
    public String editClass(@RequestParam String classId, Model model) {
        try {
            StudentClass c = dao.getClassById(classId);
            if (c != null) {
                model.addAttribute("classroom", c);
            } else {
                model.addAttribute("error", "Không tìm thấy lớp với ID: " + classId);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải lớp để chỉnh sửa: " + e.getMessage());
        }
        return "editclass";
    }

    @PostMapping("/updateclass")
    public String updateClass(@RequestParam String classId, @RequestParam String className, Model model) {
        try {
            StudentClass c = dao.getClassById(classId);
            if (c != null) {
                c.setClassName(className);
                dao.updateClass(c);
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
            dao.deleteClass(classId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa lớp: " + e.getMessage());
        }
        return "redirect:/classlist";
    }
}