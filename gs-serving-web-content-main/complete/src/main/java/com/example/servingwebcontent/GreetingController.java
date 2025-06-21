package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(HttpSession session, Model model) {
		if (session.getAttribute("isLoggedIn") == null) {
			return "redirect:/login";
		}
		model.addAttribute("name", "OOP Class Admin");
		return "greeting";
	}

}
