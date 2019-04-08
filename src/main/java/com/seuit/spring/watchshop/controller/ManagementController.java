package com.seuit.spring.watchshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/manager")
public class ManagementController {
	@GetMapping("/CRUD_Products")
	public String showCRUDProductPage() {
		return "manager/CRUDProducts";
	}
}
