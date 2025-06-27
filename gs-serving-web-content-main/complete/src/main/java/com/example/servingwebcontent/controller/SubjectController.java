package com.example.servingwebcontent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.servingwebcontent.database.subjectAiven;
import com.example.servingwebcontent.database.lecturerAiven;
import com.example.servingwebcontent.model.Lecturer;
import com.example.servingwebcontent.model.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SubjectController {

    private final subjectAiven sa = new subjectAiven();
    private final lecturerAiven la = new lecturerAiven();

    @GetMapping("/subjectlist")
    public String subjectList(@RequestParam(required = false) String editId, Model model) {
        try {
            ArrayList<Subject> subjects = sa.getSubjectList();
            model.addAttribute("subjects", subjects);

            // ✅ Tạo Map: lecturerId -> lecturerName
            Map<String, String> lecturerNameMap = new HashMap<>();
            for (Subject s : subjects) {
                String lecturerId = s.getLecturerId();
                if (lecturerId != null && !lecturerId.isEmpty() && !lecturerNameMap.containsKey(lecturerId)) {
                    Lecturer lecturer = la.getLecturerById(lecturerId);
                    if (lecturer != null) {
                        lecturerNameMap.put(lecturerId, lecturer.getName());
                    }
                }
            }

            model.addAttribute("lecturerNameMap", lecturerNameMap);

        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách môn học: " + e.getMessage());
        }

        return "subjectlist";
    }

    @GetMapping("/subjectlist/add")
    public String showAddSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "addsubject";
    }

    @PostMapping("/addsubject")
    public String addSubject(@RequestParam String subjectName,
                             @RequestParam int credits,
                             @RequestParam String lecturerId,
                             Model model) {
        try {
            int randomNum = (int) (Math.random() * 1000);
            String subjectId = String.format("sub%03d", randomNum);

            Subject subject = new Subject(subjectId, subjectName, credits, lecturerId);
            sa.insertSubject(subject);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm môn học: " + e.getMessage());
            return "addsubject";
        }
        return "redirect:/subjectlist";
    }

    @GetMapping("/subjectlist/edit")
    public String editSubject(@RequestParam String subjectId, Model model) {
        try {
            Subject subject = sa.getSubjectById(subjectId);
            model.addAttribute("subject", subject);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải thông tin môn học để chỉnh sửa: " + e.getMessage());
        }
        return "editsubject";
    }

    @PostMapping("/updatesubject")
    public String updateSubject(@RequestParam String subjectId,
                                @RequestParam String subjectName,
                                @RequestParam int credits,
                                @RequestParam String lecturerId,
                                Model model) {
        try {
            Subject s = sa.getSubjectById(subjectId);
            if (s != null) {
                s.setSubjectName(subjectName);
                s.setCredits(credits);
                s.setLecturerId(lecturerId);
                sa.updateSubject(s);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật môn học: " + e.getMessage());
            return "editsubject";
        }
        return "redirect:/subjectlist";
    }

    @GetMapping("/deletesubject")
    public String deleteSubject(@RequestParam String subjectId, Model model) {
        try {
            sa.deleteSubject(subjectId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa môn học: " + e.getMessage());
        }
        return "redirect:/subjectlist";
    }
}
