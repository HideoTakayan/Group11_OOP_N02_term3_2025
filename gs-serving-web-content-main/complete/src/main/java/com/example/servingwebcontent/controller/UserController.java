package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.database.userAiven;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class UserController {

    private final userAiven ua = new userAiven();

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
            User user = new User(email, password, role);
            ua.insertUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Đăng ký thất bại: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/edituser")
    public String editUser(@RequestParam String id, Model model) {  // sửa int -> String
        User u = ua.getUserById(id);
        model.addAttribute("user", u);
        return "edituser";
    }

    @PostMapping("/updateuser")
    public String updateUser(
            @RequestParam String id,   // sửa int -> String
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role) {

        User u = new User(email, password, role);
        u.setUserID(id);
        ua.updateUser(u);
        return "redirect:/userlist";
    }

    @GetMapping("/deleteuser")
    public String deleteUser(@RequestParam String id) {  // sửa int -> String
        ua.deleteUserById(id);
        return "redirect:/userlist";
    }
}
