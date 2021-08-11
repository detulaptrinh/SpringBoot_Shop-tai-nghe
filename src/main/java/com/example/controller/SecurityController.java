package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@RequestMapping("/security/login/form")
	public String loginForm() {
		return "login-signin";
	}
	
	@RequestMapping("/security/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập");
		return "login-signin";
	}
}
