package com.seuit.spring.watchshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@GetMapping(value= {""})
	public String showAdminIndex() {
		return "admin";
	}
}
