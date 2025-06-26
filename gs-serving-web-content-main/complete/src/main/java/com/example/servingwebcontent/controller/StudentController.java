package com.example.servingwebcontent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.servingwebcontent.database.classAiven;
import com.example.servingwebcontent.database.studentAiven;
import com.example.servingwebcontent.model.Student;
import com.example.servingwebcontent.model.StudentClass;

import java.sql.Date;
import java.util.List;
import java.util.Random;

@Controller
public class StudentController {

    @GetMapping("/studentlist")
    public String studentList(@RequestParam(required = false) String editId, Model model) {
        try {
            studentAiven sa = new studentAiven();
            List<Student> students = sa.getStudentList();
            model.addAttribute("students", students);

            if (editId != null) {
                Student s = sa.getStudentById(editId);
                model.addAttribute("editStudent", s);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách sinh viên: " + e.getMessage());
        }
        return "studentlist";
    }

    @GetMapping("/studentlist/add")
    public String showAddStudentForm(Model model) {
        try {
            classAiven ca = new classAiven();
            List<StudentClass> classList = ca.getClassList();
            model.addAttribute("classList", classList);

            // Khởi tạo giá trị mặc định để tránh null khi render form
            if (!model.containsAttribute("name")) model.addAttribute("name", "");
            if (!model.containsAttribute("address")) model.addAttribute("address", "");
            if (!model.containsAttribute("email")) model.addAttribute("email", "");
            if (!model.containsAttribute("dateOfBirth")) model.addAttribute("dateOfBirth", "");
            if (!model.containsAttribute("gender")) model.addAttribute("gender", "");
            if (!model.containsAttribute("classId")) model.addAttribute("classId", "");
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách lớp: " + e.getMessage());
        }
        return "addstudent";
    }

    @PostMapping("/addstudent")
    public String addStudent(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String dateOfBirth, // Nhận String để dễ parse
            @RequestParam String gender,
            @RequestParam String classId,
            Model model) {
        try {
            // Chuyển String sang java.sql.Date
            Date dob = Date.valueOf(dateOfBirth);

            Random rand = new Random();
            String personId = "p" + rand.nextInt(100000);
            String studentId = "s" + rand.nextInt(100000);

            classAiven ca = new classAiven();
            StudentClass sc = ca.getClassById(classId);
            String className = (sc != null) ? sc.getClassName() : "";

            Student student = new Student(studentId, personId, name, address, email, dob, gender, classId, className);
            studentAiven sa = new studentAiven();
            sa.insertStudent(student);

            return "redirect:/studentlist";
        } catch (Exception e) {
            // Trả lại form với dữ liệu đã nhập và thông báo lỗi
            model.addAttribute("error", "Lỗi khi thêm sinh viên: " + e.getMessage());
            model.addAttribute("name", name);
            model.addAttribute("address", address);
            model.addAttribute("email", email);
            model.addAttribute("dateOfBirth", dateOfBirth);
            model.addAttribute("gender", gender);
            model.addAttribute("classId", classId);

            try {
                classAiven ca = new classAiven();
                model.addAttribute("classList", ca.getClassList());
            } catch (Exception ex) {
                model.addAttribute("error", "Lỗi khi tải danh sách lớp: " + ex.getMessage());
            }

            return "addstudent";
        }
    }

    @GetMapping("/studentlist/edit")
    public String editStudent(@RequestParam String studentId, Model model) {
        try {
            studentAiven sa = new studentAiven();
            Student student = sa.getStudentById(studentId);
            model.addAttribute("student", student);

            classAiven ca = new classAiven();
            model.addAttribute("classList", ca.getClassList());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải dữ liệu sinh viên để chỉnh sửa: " + e.getMessage());
        }
        return "editstudent";
    }

    @PostMapping("/updatestudent")
    public String updateStudent(
            @RequestParam String studentId,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String dateOfBirth, // Nhận String
            @RequestParam String gender,
            @RequestParam String classId,
            Model model) {
        try {
            Date dob = Date.valueOf(dateOfBirth);

            studentAiven sa = new studentAiven();
            Student s = sa.getStudentById(studentId);
            if (s != null) {
                classAiven ca = new classAiven();
                StudentClass sc = ca.getClassById(classId);
                String className = (sc != null) ? sc.getClassName() : "";

                s.setName(name);
                s.setAddress(address);
                s.setEmail(email);
                s.setDateOfBirth(dob);
                s.setGender(gender);
                s.setClassId(classId);
                s.setClassName(className);

                sa.updateStudent(s);
            }

            return "redirect:/studentlist";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật sinh viên: " + e.getMessage());

            // Đưa student lại để form edit có dữ liệu
            try {
                studentAiven sa = new studentAiven();
                Student student = sa.getStudentById(studentId);
                model.addAttribute("student", student);
                classAiven ca = new classAiven();
                model.addAttribute("classList", ca.getClassList());
            } catch (Exception ex) {
                model.addAttribute("error", "Lỗi khi tải dữ liệu: " + ex.getMessage());
            }
            return "editstudent";
        }
    }

    @GetMapping("/deletestudent")
    public String deleteStudent(@RequestParam String studentId, Model model) {
        try {
            studentAiven sa = new studentAiven();
            sa.deleteStudent(studentId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa sinh viên: " + e.getMessage());
        }
        return "redirect:/studentlist";
    }
}
