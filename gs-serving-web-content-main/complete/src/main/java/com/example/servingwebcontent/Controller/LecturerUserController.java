package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.*;
import com.example.servingwebcontent.model.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/lecturer")
public class LecturerUserController {
    private final lecturerAiven lecturerDao = new lecturerAiven();
    private final classSectionAiven classSectionDao = new classSectionAiven();
    private final registerClassAiven registerDao = new registerClassAiven();
    private final studentAiven studentDao = new studentAiven();
    private final environmentAiven envDao = new environmentAiven();
    private final examScheduleAiven examDao = new examScheduleAiven();
    private final subjectAiven subjectDao = new subjectAiven();

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Time.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(Time.valueOf(LocalTime.parse(text)));
            }
        });

        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(Date.valueOf(text));
            }
        });
    }
@GetMapping("/profile")
public String viewProfile(HttpSession session, Model model) {
    try {
        String email = (String) session.getAttribute("userEmail");
        if (email == null) return "redirect:/login";

        Lecturer lecturer = lecturerDao.getLecturerByEmail(email);
        if (lecturer == null) return "redirect:/login";

        model.addAttribute("lecturer", lecturer);
        model.addAttribute("editing", false);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi tải trang hồ sơ: " + e.getMessage());
    }
    return "LecturerUser/profile";
}

@GetMapping("/profile/edit")
public String editProfileForm(HttpSession session, Model model) {
    try {
        String email = (String) session.getAttribute("userEmail");
        Lecturer lecturer = lecturerDao.getLecturerByEmail(email);
        model.addAttribute("lecturer", lecturer);
        model.addAttribute("editing", true);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi tải form sửa hồ sơ: " + e.getMessage());
    }
    return "LecturerUser/profile";
}

@PostMapping("/profile/edit")
public String updateProfile(@ModelAttribute Lecturer lecturer, HttpSession session, Model model) {
    try {
        Lecturer old = lecturerDao.getLecturerByEmail((String) session.getAttribute("userEmail"));
        if (old == null) return "redirect:/login";

        lecturer.setLecturerId(old.getLecturerId());
        lecturer.setPersonId(old.getPersonId());
        lecturerDao.updateLecturer(lecturer);
        session.setAttribute("userName", lecturer.getName());
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi cập nhật hồ sơ: " + e.getMessage());
    }
    return "redirect:/lecturer/profile";
}

 @GetMapping("/class-sections")
public String viewManagedClassSections(HttpSession session, Model model) {
    try {
        Lecturer lecturer = lecturerDao.getLecturerByEmail((String) session.getAttribute("userEmail"));
        List<ClassSection> sections = classSectionDao.getAllClassSections().stream()
                .filter(c -> c.getLecturerId().equals(lecturer.getLecturerId()))
                .collect(Collectors.toList());
        model.addAttribute("classSections", sections);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi tải danh sách lớp: " + e.getMessage());
    }
    return "LecturerUser/class_sections";
}

    @GetMapping("/class-section/add-form")
    public String showAddClassSectionForm() {
        return "LecturerUser/add_class_section";
    }

@PostMapping("/class-section/add")
public String addClassSection(@RequestParam String classId, HttpSession session, Model model) {
    try {
        Lecturer lecturer = lecturerDao.getLecturerByEmail((String) session.getAttribute("userEmail"));
        ClassSection cs = classSectionDao.getClassSectionById(classId);
        if (cs != null) {
            cs.setLecturerId(lecturer.getLecturerId());
            classSectionDao.updateClassSection(cs);
        }
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi thêm lớp vào giảng viên: " + e.getMessage());
    }
    return "redirect:/lecturer/class-sections";
}

@GetMapping("/class-section/remove")
public String removeClassSection(@RequestParam String classId, Model model) {
    try {
        ClassSection cs = classSectionDao.getClassSectionById(classId);
        if (cs != null) {
            cs.setLecturerId(null);
            classSectionDao.updateClassSection(cs);
        }
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi gỡ lớp học: " + e.getMessage());
    }
    return "redirect:/lecturer/class-sections";
}

@GetMapping("/students")
public String viewStudentsInManagedSections(HttpSession session, Model model) {
    try {
        Lecturer lecturer = lecturerDao.getLecturerByEmail((String) session.getAttribute("userEmail"));
        List<ClassSection> mySections = classSectionDao.getAllClassSections().stream()
                .filter(c -> c.getLecturerId().equals(lecturer.getLecturerId()))
                .collect(Collectors.toList());

        List<RegisterClassSection> allRegisters = registerDao.getRegisterClassList();
        Map<ClassSection, List<Map<String, Object>>> grouped = new LinkedHashMap<>();

        for (ClassSection section : mySections) {
            List<Map<String, Object>> studentList = allRegisters.stream()
                    .filter(r -> r.getClassSectionId().equals(section.getClassId()))
                    .map(r -> {
                        Student s = studentDao.getStudentById(r.getStudentId());
                        if (s == null)
                            return null;
                        Map<String, Object> map = new HashMap<>();
                        map.put("student", s);
                        map.put("registerId", r.getRegisterId());
                        return map;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            grouped.put(section, studentList);
        }

        model.addAttribute("groupedStudents", grouped);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi tải danh sách sinh viên: " + e.getMessage());
    }
    return "LecturerUser/students_in_classes";
}

@GetMapping("/student/remove")
public String removeStudentFromClass(@RequestParam String registerId, Model model) {
    try {
        registerDao.deleteRegisterClass(registerId);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi xóa sinh viên khỏi lớp: " + e.getMessage());
    }
    return "redirect:/lecturer/students";
}
@GetMapping("/schedule")
public String viewSchedule(HttpSession session, Model model) {
    try {
        Lecturer lecturer = lecturerDao.getLecturerByEmail((String) session.getAttribute("userEmail"));
        List<Environment> schedules = envDao.getEnvironments().stream()
                .filter(e -> lecturer.getName().equals(e.getLecturerName()))
                .collect(Collectors.toList());

        model.addAttribute("schedules", schedules);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi tải lịch học: " + e.getMessage());
    }
    return "LecturerUser/schedule_list";
}

@GetMapping("/schedule/edit")
public String editSchedule(@RequestParam String enviromentId, Model model) {
    try {
        Environment env = envDao.getEnvironmentById(enviromentId);
        model.addAttribute("schedule", env);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi tải lịch học để sửa: " + e.getMessage());
    }
    return "LecturerUser/edit_schedule";
}

@PostMapping("/schedule/update")
public String updateSchedule(@ModelAttribute Environment env, HttpSession session, Model model) {
    try {
        Environment original = envDao.getEnvironmentById(env.getEnviromentId());
        if (original == null) {
            return "redirect:/lecturer/schedule";
        }

        env.setClassId(original.getClassId());
        env.setClassName(original.getClassName());
        env.setSubjectName(original.getSubjectName());
        env.setLecturerName(original.getLecturerName());

        envDao.updateEnvironment(env);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi cập nhật lịch học: " + e.getMessage());
    }
    return "redirect:/lecturer/schedule";
}

@GetMapping("/schedule/delete")
public String deleteSchedule(@RequestParam String enviromentId, Model model) {
    try {
        envDao.deleteEnvironment(enviromentId);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi xóa lịch học: " + e.getMessage());
    }
    return "redirect:/lecturer/schedule";
}

    @GetMapping("/schedule/add")
    public String addScheduleForm(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        Lecturer lecturer = lecturerDao.getLecturerByEmail(email);

        List<ClassSection> sections = classSectionDao.getAllClassSections().stream()
                .filter(c -> c.getLecturerId().equals(lecturer.getLecturerId()))
                .collect(Collectors.toList());

        model.addAttribute("schedule", new Environment());
        model.addAttribute("managedSections", sections);
        return "LecturerUser/add_schedule";
    }

@PostMapping("/schedule/add")
public String addSchedule(@ModelAttribute Environment env, HttpSession session, Model model) {
    try {
        env.setEnviromentId(UUID.randomUUID().toString());
        String email = (String) session.getAttribute("userEmail");
        Lecturer lecturer = lecturerDao.getLecturerByEmail(email);

        ClassSection cs = classSectionDao.getClassSectionById(env.getClassId());
        if (cs != null) {
            env.setClassName(cs.getClassName());
            Subject subject = subjectDao.getSubjectById(cs.getSubjectId());
            if (subject != null) {
                env.setSubjectName(subject.getSubjectName());
            } else {
                env.setSubjectName("(Không rõ)");
            }
        }

        env.setLecturerName(lecturer.getName());
        envDao.insertEnvironment(env);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi thêm lịch học: " + e.getMessage());
    }
    return "redirect:/lecturer/schedule";
}

    @GetMapping("/exams")
    public String viewExams(Model model, HttpSession session) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null) return "redirect:/login";

        Lecturer lecturer = lecturerDao.getLecturerByEmail(email);
        if (lecturer == null) return "redirect:/login";

        List<Subject> subjectList = subjectDao.getSubjectList().stream()
                .filter(s -> lecturer.getLecturerId().equals(s.getLecturerId()))
                .collect(Collectors.toList());

        List<String> subjectsManaged = subjectList.stream()
                .map(Subject::getSubjectName)
                .collect(Collectors.toList());

        List<ExamSchedule> allExams = examDao.getExamSchedules();
        List<ExamSchedule> myExams = allExams.stream()
                .filter(e -> subjectsManaged.contains(e.getSubjectName()))
                .collect(Collectors.toList());

        model.addAttribute("exams", myExams);
        return "LecturerUser/exam_list";
    }

    @GetMapping("/exam/add")
    public String addExamForm(Model model, HttpSession session) {
        String email = (String) session.getAttribute("userEmail");
        Lecturer lecturer = lecturerDao.getLecturerByEmail(email);

        List<Subject> subjects = subjectDao.getSubjectList().stream()
                .filter(s -> s.getLecturerId().equals(lecturer.getLecturerId()))
                .collect(Collectors.toList());

        model.addAttribute("exam", new ExamSchedule());
        model.addAttribute("subjects", subjects);
        return "LecturerUser/add_exam";
    }

@PostMapping("/exam/add")
public String addExam(@RequestParam String subjectName,
                      @RequestParam String examDate,
                      @RequestParam String startTime,
                      @RequestParam int durationMinutes,
                      @RequestParam String examFormat,
                      @RequestParam String location,
                      HttpSession session,
                      Model model) {
    try {
        String email = (String) session.getAttribute("userEmail");
        Lecturer lecturer = lecturerDao.getLecturerByEmail(email);

        List<String> subjectsManaged = subjectDao.getSubjectList().stream()
                .filter(s -> s.getLecturerId().equals(lecturer.getLecturerId()))
                .map(Subject::getSubjectName)
                .collect(Collectors.toList());

        if (!subjectsManaged.contains(subjectName)) {
            return "redirect:/lecturer/exams";
        }

        ExamSchedule exam = new ExamSchedule();
        exam.setSubjectName(subjectName);
        exam.setExamDate(Date.valueOf(LocalDate.parse(examDate)));
        exam.setStartTime(Time.valueOf(LocalTime.parse(startTime)));
        exam.setDurationMinutes(durationMinutes);
        exam.setExamFormat(examFormat);
        exam.setLocation(location);

        examDao.insertExamSchedule(exam);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi thêm lịch thi: " + e.getMessage());
    }
    return "redirect:/lecturer/exams";
}

@GetMapping("/exam/edit")
public String editExam(@RequestParam String subjectName, Model model) {
    try {
        ExamSchedule exam = examDao.getExamBySubject(subjectName);
        model.addAttribute("exam", exam);
        model.addAttribute("originalSubjectName", subjectName);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi tải form sửa lịch thi: " + e.getMessage());
    }
    return "LecturerUser/edit_exam";
}

@PostMapping("/exam/update")
public String updateExam(@RequestParam String originalSubjectName,
                         @ModelAttribute ExamSchedule exam,
                         Model model) {
    try {
        examDao.updateExamSchedule(originalSubjectName, exam);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi cập nhật lịch thi: " + e.getMessage());
    }
    return "redirect:/lecturer/exams";
}

@GetMapping("/exam/delete")
public String deleteExam(@RequestParam String subjectName, Model model) {
    try {
        examDao.deleteExamSchedule(subjectName);
    } catch (Exception e) {
        model.addAttribute("error", "Lỗi khi xóa lịch thi: " + e.getMessage());
    }
    return "redirect:/lecturer/exams";
}

    @GetMapping("/home")
    public String homeLecturer(HttpSession session) {
        if (session.getAttribute("userEmail") == null)
            return "redirect:/login";
        return "LecturerUser/home-lecturer";
    }
}
