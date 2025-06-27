package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.*;
import com.example.servingwebcontent.model.*;

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
    private final classAiven classAivenDao = new classAiven();

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

        StudentClass studentClass = classAivenDao.getClassById(student.getClassId());
        String className = (studentClass != null) ? studentClass.getClassName() : "Chưa rõ";
        model.addAttribute("className", className);

        if (edit != null && edit) {
            List<StudentClass> classList = classAivenDao.getClassList();
            model.addAttribute("allClasses", classList);
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

            StudentClass studentClass = classAivenDao.getClassById(student.getClassId());
            String className = (studentClass != null) ? studentClass.getClassName() : "Chưa rõ";
            model.addAttribute("className", className);

            List<StudentClass> classList = classAivenDao.getClassList();
            model.addAttribute("allClasses", classList);

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

        Map<String, String> subjectNameMap = new HashMap<>();
        Map<String, String> lecturerNameMap = new HashMap<>();

        subjectAiven subjectDao = new subjectAiven();
        lecturerAiven lecturerDao = new lecturerAiven();

        for (RegisterClassSection rc : registeredClasses) {
            ClassSection cs = classDao.getClassSectionById(rc.getClassSectionId());
            if (cs != null) {
                Subject subject = subjectDao.getSubjectById(cs.getSubjectId());
                if (subject != null) {
                    subjectNameMap.put(rc.getRegisterId(), subject.getSubjectName());

                    Lecturer lecturer = lecturerDao.getLecturerById(subject.getLecturerId());
                    if (lecturer != null) {
                        lecturerNameMap.put(rc.getRegisterId(), lecturer.getName());
                    }
                }
            }
        }

        model.addAttribute("registeredClasses", registeredClasses);
        model.addAttribute("subjectNameMap", subjectNameMap);
        model.addAttribute("lecturerNameMap", lecturerNameMap);

        return "StudentUser/registered_classes";
    }

    @PostMapping("/unregister-class")
    public String unregisterClass(@RequestParam("registerId") String registerId,
                                   HttpSession session) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null)
            return "redirect:/login";

        try {
            registerDao.deleteRegisterClass(registerId);
        } catch (Exception e) {
            // log nếu cần
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
