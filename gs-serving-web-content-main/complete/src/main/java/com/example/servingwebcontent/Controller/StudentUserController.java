package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.registerClassAiven;
import com.example.servingwebcontent.database.studentAiven;
import com.example.servingwebcontent.database.classSectionAiven;
import com.example.servingwebcontent.database.environmentAiven;
import com.example.servingwebcontent.model.RegisterClassSection;
import com.example.servingwebcontent.model.Student;
import com.example.servingwebcontent.model.ClassSection;
import com.example.servingwebcontent.model.Environment;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentUserController {

    private final studentAiven studentDao = new studentAiven();
    private final registerClassAiven registerDao = new registerClassAiven();
    private final classSectionAiven classDao = new classSectionAiven();
    private final environmentAiven envDao = new environmentAiven();

    @GetMapping("/profile")
    public String viewProfile(@RequestParam(value = "edit", required = false) Boolean edit,
            HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null)
            return "redirect:/login";

        Student student = studentDao.getStudentByEmail(email);
        if (student == null)
            return "redirect:/login";

        model.addAttribute("student", student);
        model.addAttribute("editing", edit != null && edit);
        model.addAttribute("role", "Sinh viên");
        model.addAttribute("className", student.getClassName());

        if (edit != null && edit) {
            List<String> classNames = classDao.getAllClassSections().stream()
                    .map(ClassSection::getClassName)
                    .distinct()
                    .collect(Collectors.toList());
            model.addAttribute("allClassNames", classNames);
        }

        return "StudentUser/profile";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute Student student, HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null)
            return "redirect:/login";

        Student oldStudent = studentDao.getStudentByEmail(email);
        if (oldStudent == null)
            return "redirect:/login";

        student.setStudentId(oldStudent.getStudentId());
        student.setPersonId(oldStudent.getPersonId());

        try {
            studentDao.updateStudent(student);
            session.setAttribute("userName", student.getName());
            return "redirect:/student/profile";
        } catch (Exception e) {
            model.addAttribute("error", "Cập nhật thất bại: " + e.getMessage());
            model.addAttribute("student", student);
            model.addAttribute("editing", true);
            model.addAttribute("role", "Sinh viên");
            model.addAttribute("className", student.getClassName());

            List<String> classNames = classDao.getAllClassSections().stream()
                    .map(ClassSection::getClassName)
                    .distinct()
                    .collect(Collectors.toList());
            model.addAttribute("allClassNames", classNames);

            return "StudentUser/profile";
        }
    }

    @GetMapping("/registered-classes")
    public String viewRegisteredClasses(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null)
            return "redirect:/login";

        Student student = studentDao.getStudentByEmail(email);
        if (student == null)
            return "redirect:/login";

        List<RegisterClassSection> registeredClasses = registerDao.getRegisterClassList()
                .stream()
                .filter(rc -> rc.getStudentId().equals(student.getStudentId()))
                .peek(rc -> {
                    ClassSection cs = classDao.getClassSectionById(rc.getClassSectionId());
                    if (cs != null) {
                        rc.setClassName(cs.getClassName());
                    }
                })
                .collect(Collectors.toList());

        model.addAttribute("registeredClasses", registeredClasses);
        return "StudentUser/registered_classes";
    }

    // Thêm phương thức huỷ đăng ký lớp
    @PostMapping("/unregister-class")
    public String unregisterClass(@RequestParam("registerId") String registerId,
            HttpSession session) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null)
            return "redirect:/login";

        try {
            registerDao.deleteRegisterClass(registerId);
        } catch (Exception e) {
            // Có thể thêm log hoặc chuyển thông báo lỗi qua Model nếu cần
        }

        return "redirect:/student/registered-classes";
    }

    @GetMapping("/class-schedule")
    public String viewClassSchedule(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null)
            return "redirect:/login";

        Student student = studentDao.getStudentByEmail(email);
        if (student == null)
            return "redirect:/login";

        List<RegisterClassSection> registeredClasses = registerDao.getRegisterClassList()
                .stream()
                .filter(rc -> rc.getStudentId().equals(student.getStudentId()))
                .collect(Collectors.toList());

        List<String> classIds = registeredClasses.stream()
                .map(RegisterClassSection::getClassSectionId)
                .collect(Collectors.toList());

        List<Environment> allEnvs = envDao.getEnvironments();
        List<Environment> mySchedules = allEnvs.stream()
                .filter(env -> classIds.contains(env.getClassId()))
                .collect(Collectors.toList());

        Map<String, List<Environment>> scheduleMap = mySchedules.stream()
                .collect(Collectors.groupingBy(Environment::getSubjectName));

        model.addAttribute("scheduleMap", scheduleMap);
        return "StudentUser/class_schedule";
    }

    @GetMapping("/register-class")
    public String showAvailableClasses(HttpSession session, Model model,
            @RequestParam(value = "error", required = false) String error) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null)
            return "redirect:/login";

        Student student = studentDao.getStudentByEmail(email);
        if (student == null)
            return "redirect:/login";

        List<RegisterClassSection> registeredClasses = registerDao.getRegisterClassList()
                .stream()
                .filter(rc -> rc.getStudentId().equals(student.getStudentId()))
                .collect(Collectors.toList());

        Set<String> registeredClassIds = registeredClasses.stream()
                .map(RegisterClassSection::getClassSectionId)
                .collect(Collectors.toSet());

        List<ClassSection> allClasses = classDao.getAllClassSections();

        List<ClassSection> availableClasses = allClasses.stream()
                .filter(c -> !registeredClassIds.contains(c.getClassId()))
                .collect(Collectors.toList());

        model.addAttribute("availableClasses", availableClasses);

        if (error != null) {
            model.addAttribute("error", error);
        }

        return "StudentUser/register_class";
    }

    @PostMapping("/register-class")
    public String registerClass(@RequestParam("classId") String classId, HttpSession session) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null)
            return "redirect:/login";

        Student student = studentDao.getStudentByEmail(email);
        if (student == null)
            return "redirect:/login";

        RegisterClassSection rc = new RegisterClassSection(
                UUID.randomUUID().toString(),
                student.getStudentId(),
                classId);

        registerDao.insertRegisterClass(rc);

        return "redirect:/student/register-class";
    }

    @GetMapping("/home")
    public String homeStudent(HttpSession session) {
        if (session.getAttribute("userEmail") == null)
            return "redirect:/login";
        return "StudentUser/home-student";
    }
}
