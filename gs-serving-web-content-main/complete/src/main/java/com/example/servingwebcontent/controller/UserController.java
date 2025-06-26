package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.lecturerAiven;
import com.example.servingwebcontent.database.personAiven;
import com.example.servingwebcontent.database.studentAiven;
import com.example.servingwebcontent.database.userAiven;
import com.example.servingwebcontent.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@Controller
public class UserController {

    private final userAiven ua = new userAiven();
    private final personAiven pa = new personAiven();
    private final studentAiven sa = new studentAiven();
    private final lecturerAiven la = new lecturerAiven();

    @GetMapping("/userlist")
    public String userList(Model model, HttpSession session) {
        if (!"admin".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }

        ArrayList<User> users = ua.getAllUsers();
        model.addAttribute("users", users);
        return "userlist";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            Model model) {

        if (ua.findByEmail(email) != null) {
            model.addAttribute("error", "Email đã tồn tại.");
            return "register";
        }

        try {
            // Tạo person_id mới
            String personId = UUID.randomUUID().toString();

            // Chèn bản ghi vào bảng person nếu chưa tồn tại
            pa.insertPersonWithIdIfNotExists(personId, email.trim());

            // Nếu role là student => tạo bản ghi student trống
            if ("student".equalsIgnoreCase(role.trim())) {
                sa.insertStudentWithPersonId(personId);
            }

            // Nếu role là lecturer => tạo bản ghi lecturer trống
            if ("lecturer".equalsIgnoreCase(role.trim())) {
                la.insertLecturerWithPersonId(personId);
            }

            // Thêm người dùng vào bảng users
            User user = new User(email.trim(), password.trim(), role.trim());
            ua.insertUser(user);

            return "redirect:/login";

        } catch (Exception e) {
            model.addAttribute("error", "Đăng ký thất bại: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/edituser")
    public String editUser(@RequestParam String id, Model model) {
        User u = ua.getUserById(id);
        model.addAttribute("user", u);
        return "edituser";
    }

    @PostMapping("/updateuser")
    public String updateUser(
            @RequestParam String id,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role) {

        User u = new User(email, password, role);
        u.setUserID(id);
        ua.updateUser(u);
        return "redirect:/userlist";
    }

    @GetMapping("/deleteuser")
    public String deleteUser(@RequestParam String id) {
        ua.deleteUserById(id);
        return "redirect:/userlist";
    }
}
