package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.database.lecturerAiven;
import com.example.servingwebcontent.database.studentAiven;
import com.example.servingwebcontent.database.userAiven;
import com.example.servingwebcontent.model.Lecturer;
import com.example.servingwebcontent.model.Student;
import com.example.servingwebcontent.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            HttpSession session,
            Model model) {
        try {
            // ✅ Trường hợp đăng nhập admin đặc biệt
            if ("admin@gmail.com".equals(email) && "admin123".equals(password) && "admin".equals(role)) {
                session.setAttribute("isLoggedIn", true);
                session.setAttribute("role", "admin");
                session.setAttribute("userEmail", email);
                session.setAttribute("userName", "Admin");
                return "redirect:/studentlist";
            }

            // ✅ Kiểm tra thông tin người dùng trong bảng users
            userAiven ua = new userAiven();
            User user = ua.findByEmailAndPassword(email, password);

            if (user == null || !user.getRole().equals(role)) {
                model.addAttribute("error", "Sai email, mật khẩu hoặc vai trò.");
                return "login";
            }

            // ✅ Lưu thông tin cơ bản
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("role", role);
            session.setAttribute("userEmail", email);

            // ✅ Phân loại theo vai trò
            if ("student".equals(role)) {
                studentAiven sa = new studentAiven();
                Student s = sa.getStudentByEmail(email);
                if (s != null) {
                    session.setAttribute("userName", s.getName());
                    return "redirect:/student/home";
                }
            }

            if ("lecturer".equals(role)) {
                lecturerAiven la = new lecturerAiven();
                Lecturer l = la.getLecturerByEmail(email);
                if (l != null) {
                    session.setAttribute("userName", l.getName());
                    return "redirect:/lecturer/home";
                }
            }

            model.addAttribute("error", "Không tìm thấy thông tin người dùng.");
            return "login";

        } catch (Exception e) {
            model.addAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
