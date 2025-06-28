package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.classAiven;
import com.example.servingwebcontent.database.studentAiven;
import com.example.servingwebcontent.model.StudentClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class ClassController {

    private final classAiven dao = new classAiven(); // class DAO instance dùng chung

@GetMapping("/classlist")
public String classList(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String classId,
        Model model) {
    try {
        ArrayList<StudentClass> classes;
        if (keyword != null && !keyword.trim().isEmpty()) {
            classes = dao.searchClassesByName(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            classes = dao.getClassList();
        }
        model.addAttribute("classes", classes);

        // Nếu chọn một lớp cụ thể để xem danh sách sinh viên
        if (classId != null && !classId.trim().isEmpty()) {
            studentAiven studentDao = new studentAiven();
            model.addAttribute("studentsInClass", studentDao.getStudentsByClassId(classId));
            model.addAttribute("selectedClassId", classId);
        }

    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi tải danh sách lớp: " + e.getMessage());
    }

    return "classlist";
}


    @GetMapping("/classlist/add")
    public String showAddClassForm(Model model) {
        return "addclass";
    }

    @PostMapping("/addclass")
    public String addClass(@RequestParam String classId,
            @RequestParam String className,
            Model model) {
        try {
            StudentClass newClass = new StudentClass(classId, className);
            dao.insertClass(newClass);
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
    public String updateClass(@RequestParam String classId,
            @RequestParam String className,
            Model model) {
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
