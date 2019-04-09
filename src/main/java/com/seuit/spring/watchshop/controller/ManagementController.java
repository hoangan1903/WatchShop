package com.seuit.spring.watchshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/manager")
public class ManagementController {
	@GetMapping(value="/CRUD_Products")
	public String showCRUDProductPage() {
		return "manager/CRUDProducts";
	}
}
