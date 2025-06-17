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
        studentAiven sa = new studentAiven();
        ArrayList<Student> students = sa.getStudentList();
        model.addAttribute("students", students);

        if (editId != null) {
            Student s = sa.getStudentById(editId);
            model.addAttribute("editStudent", s);
        }

        return "studentlist";
    }

    @GetMapping("/studentlist/add")
    public String showAddStudentForm(Model model) {
        classAiven ca = new classAiven();
        List<ClassRoom> classList = ca.getClassList();
        model.addAttribute("classList", classList);
        return "addstudent";
    }

    @PostMapping("/addstudent")
    public String addStudent(@RequestParam String name,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String major,
            @RequestParam String classId,
            Model model) {
        Random rand = new Random();
        String personId = "p" + rand.nextInt(1000);
        String studentId = "s" + rand.nextInt(1000);

        Student student = new Student(studentId, personId, name, address, email, major, classId);
        studentAiven sa = new studentAiven();
        sa.insertStudent(student);

        return "redirect:/studentlist";
    }

    @GetMapping("/studentlist/edit")
    public String editStudent(@RequestParam String studentId, Model model) {
        studentAiven sa = new studentAiven();
        Student student = sa.getStudentById(studentId);
        model.addAttribute("student", student);
        classAiven ca = new classAiven();
        model.addAttribute("classList", ca.getClassList());

        return "editstudent";
    }

    @PostMapping("/updatestudent")
    public String updateStudent(@RequestParam String studentId,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String major,
            @RequestParam String classId) {
        studentAiven sa = new studentAiven();
        Student s = sa.getStudentById(studentId);
        if (s != null) {
            s.setName(name);
            s.setAddress(address);
            s.setEmail(email);
            s.setMajor(major);
            s.setClassId(classId);
            sa.updateStudent(s);
        }
        return "redirect:/studentlist";
    }

    @GetMapping("/deletestudent")
    public String deleteStudent(@RequestParam String studentId) {
        studentAiven sa = new studentAiven();
        sa.deleteStudent(studentId);
        return "redirect:/studentlist";
    }
}
