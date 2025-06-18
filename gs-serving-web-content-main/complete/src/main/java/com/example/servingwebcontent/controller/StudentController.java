package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.servingwebcontent.database.classAiven;
import com.example.servingwebcontent.database.studentAiven;

import com.example.servingwebcontent.model.ClassRoom;
import com.example.servingwebcontent.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class StudentController {

    @GetMapping("/studentlist")
    public String studentList(@RequestParam(required = false) String editId, Model model) {
        studentAiven sa = null;
        try {
            sa = new studentAiven();
            ArrayList<Student> students = sa.getStudentList();
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
        classAiven ca = null;
        try {
            ca = new classAiven();
            List<ClassRoom> classList = ca.getClassList();
            model.addAttribute("classList", classList);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách lớp: " + e.getMessage());
        } finally {

        }
        return "addstudent";
    }

    @PostMapping("/addstudent")
    public String addStudent(@RequestParam String name,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String major,
            @RequestParam String classId,
            Model model) {
        studentAiven sa = null;
        try {
            Random rand = new Random();
            String personId = "p" + rand.nextInt(1000);
            String studentId = "s" + rand.nextInt(1000);

            Student student = new Student(studentId, personId, name, address, email, major, classId);
            sa = new studentAiven();
            sa.insertStudent(student);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm sinh viên: " + e.getMessage());
            return "addstudent";
        } finally {

        }
        return "redirect:/studentlist";
    }

    @GetMapping("/studentlist/edit")
    public String editStudent(@RequestParam String studentId, Model model) {
        studentAiven sa = null;
        classAiven ca = null;
        try {
            sa = new studentAiven();
            Student student = sa.getStudentById(studentId);
            model.addAttribute("student", student);

            ca = new classAiven();
            model.addAttribute("classList", ca.getClassList());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải dữ liệu sinh viên để chỉnh sửa: " + e.getMessage());
        } finally {
        }
        return "editstudent";
    }

    @PostMapping("/updatestudent")
    public String updateStudent(@RequestParam String studentId,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String major,
            @RequestParam String classId,
            Model model) {
        studentAiven sa = null;
        try {
            sa = new studentAiven();
            Student s = sa.getStudentById(studentId);
            if (s != null) {
                s.setName(name);
                s.setAddress(address);
                s.setEmail(email);
                s.setMajor(major);
                s.setClassId(classId);
                sa.updateStudent(s);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật sinh viên: " + e.getMessage());
            return "editstudent";
        } finally {
        }
        return "redirect:/studentlist";
    }

    @GetMapping("/deletestudent")
    public String deleteStudent(@RequestParam String studentId, Model model) {
        studentAiven sa = null;
        try {
            sa = new studentAiven();
            sa.deleteStudent(studentId);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa sinh viên: " + e.getMessage());
        } finally {

        }
        return "redirect:/studentlist";
    }
}
