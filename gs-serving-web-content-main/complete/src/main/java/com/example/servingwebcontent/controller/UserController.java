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

    // ✅ Danh sách user (chỉ admin xem được)
    @GetMapping("/userlist")
    public String userList(Model model, HttpSession session) {
        if (!"admin".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }

        userAiven ua = new userAiven();
        ArrayList<User> users = ua.userAivenList();
        model.addAttribute("users", users);
        return "userlist";
    }

    // ✅ Hiển thị form đăng ký
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // ✅ Xử lý form đăng ký
    @PostMapping("/register")
    public String registerUser(
            @RequestParam String userName,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        userAiven ua = new userAiven();

        // Kiểm tra email đã tồn tại
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
            user.setRole("user"); // mặc định là user

            ua.insertUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Đăng ký thất bại: " + e.getMessage());
            return "register";
        }
    }

    // ✅ Hiển thị form sửa user
    @GetMapping("/edituser")
    public String editUser(@RequestParam String id, Model model) {
        userAiven ua = new userAiven();
        User u = ua.getUserById(id);
        model.addAttribute("user", u);
        return "edituser";
    }

    // ✅ Xử lý cập nhật user
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

        userAiven ua = new userAiven();
        ua.updateUser(u);
        return "redirect:/userlist";
    }

    // ✅ Xóa user
    @GetMapping("/deleteuser")
    public String deleteUser(@RequestParam String id) {
        userAiven ua = new userAiven();
        ua.deleteUserById(id);
        return "redirect:/userlist";
    }
}
