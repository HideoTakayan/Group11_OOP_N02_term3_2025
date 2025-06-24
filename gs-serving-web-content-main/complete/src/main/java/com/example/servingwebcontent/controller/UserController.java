package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.database.userAiven;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class UserController {

    private final userAiven ua = new userAiven();  // ✅ Tái sử dụng DAO

    @GetMapping("/userlist")
    public String userList(Model model, HttpSession session) {
        if (!"admin".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }

        ArrayList<User> users = ua.getAllUsers(); // ✅ dùng tên hàm mới
        model.addAttribute("users", users);
        return "userlist";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String userName,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        if (ua.findByEmail(email) != null) {
            model.addAttribute("error", "Email đã tồn tại.");
            return "register";
        }

        try {
            User user = new User();
            user.setUserName(userName);
            user.setAddress(address);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole("user");

            ua.insertUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Đăng ký thất bại: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/edituser")
    public String editUser(@RequestParam int id, Model model) {
        User u = ua.getUserById(String.valueOf(id));  // ✅ ép kiểu lại vì DAO dùng String
        model.addAttribute("user", u);
        return "edituser";
    }

    @PostMapping("/updateuser")
    public String updateUser(
            @RequestParam int id,
            @RequestParam String userName,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role) {

        User u = new User();
        u.setUserID(id);
        u.setUserName(userName);
        u.setAddress(address);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole(role);

        ua.updateUser(u);
        return "redirect:/userlist";
    }

    @GetMapping("/deleteuser")
    public String deleteUser(@RequestParam int id) {
        ua.deleteUserById(String.valueOf(id));  // ✅ DAO dùng String
        return "redirect:/userlist";
    }
}
