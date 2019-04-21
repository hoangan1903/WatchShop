package com.seuit.spring.watchshop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.seuit.spring.watchshop.entity.CustomUserDetail;

@Controller
public class HomeController {
	@GetMapping(value = { "/" })
	public String showIndex() {
		return "index";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@GetMapping("/admin")
	public String showAdminIndex() {
		return "admin/index";
	}

	@GetMapping("/manager")
	public String showIndexManager() {
		return "manager/index";
	}

	@GetMapping("/logout")
	public String execLogout() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegisterPage() {
		return "register";
	}
}
