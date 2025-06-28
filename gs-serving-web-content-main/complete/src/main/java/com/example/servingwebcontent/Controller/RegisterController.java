package com.example.servingwebcontent.Controller;

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
public class RegisterController {

    private final registerClassAiven registerDAO = new registerClassAiven();
    private final studentAiven studentDAO = new studentAiven();
    private final classSectionAiven classSectionDAO = new classSectionAiven();

@GetMapping("/registerclasslist")
public String viewRegisterClassList(@RequestParam(required = false) String keyword, Model model) {
    try {
        List<RegisterClassSection> registerList;
        if (keyword != null && !keyword.isEmpty()) {
            registerList = registerDAO.searchRegisterClassList(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            registerList = registerDAO.getRegisterClassList();
        }
        model.addAttribute("registerList", registerList);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi tải danh sách đăng ký lớp: " + e.getMessage());
    }
    return "registerclasslist";
}


    @GetMapping("/addregisterclass")
    public String showAddForm(Model model) {
        try {
            model.addAttribute("studentList", studentDAO.getStudentList());
            model.addAttribute("classSectionList", classSectionDAO.getAllClassSections());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải dữ liệu thêm đăng ký: " + e.getMessage());
        }
        return "addregisterclass";
    }

    @PostMapping("/addregisterclass")
    public String addRegisterClass(@RequestParam String studentId,
                                   @RequestParam String classSectionId,
                                   Model model) {
        try {
            String registerId = UUID.randomUUID().toString();
            RegisterClassSection rc = new RegisterClassSection(registerId, studentId, classSectionId);
            registerDAO.insertRegisterClass(rc);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm đăng ký lớp: " + e.getMessage());
            return "addregisterclass";
        }
        return "redirect:/registerclasslist";
    }

    @GetMapping("/registerclass/edit")
    public String editRegisterClass(@RequestParam("id") String registerId, Model model) {
        try {
            RegisterClassSection rc = registerDAO.getRegisterClassById(registerId);
            model.addAttribute("registerClass", rc);
            model.addAttribute("studentList", studentDAO.getStudentList());
            model.addAttribute("classSectionList", classSectionDAO.getAllClassSections());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải thông tin đăng ký để chỉnh sửa: " + e.getMessage());
        }
        return "editregisterclass";
    }

    @PostMapping("/updateregisterclass")
    public String updateRegisterClass(@RequestParam String registerId,
                                      @RequestParam String studentId,
                                      @RequestParam String classSectionId,
                                      Model model) {
        try {
            RegisterClassSection rc = new RegisterClassSection(registerId, studentId, classSectionId);
            registerDAO.updateRegisterClass(rc);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật đăng ký lớp: " + e.getMessage());
            return "editregisterclass";
        }
        return "redirect:/registerclasslist";
    }

    @GetMapping("/deleteregisterclass")
    public String deleteRegisterClass(@RequestParam("id") String registerId, Model model) {
        try {
            registerDAO.deleteRegisterClass(registerId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa đăng ký lớp: " + e.getMessage());
        }
        return "redirect:/registerclasslist";
    }
}
