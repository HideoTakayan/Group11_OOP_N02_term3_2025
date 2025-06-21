package com.example.servingwebcontent.controller;

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
			HttpSession session,
			Model model) {

		if (email.equals("admin@gmail.com") && password.equals("admin123")) {

			session.setAttribute("isLoggedIn", true);
			return "redirect:/studentlist";
		} else {
			model.addAttribute("error", "Email hoặc mật khẩu không đúng!");
			return "login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
