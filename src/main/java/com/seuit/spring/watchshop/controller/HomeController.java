package com.seuit.spring.watchshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seuit.spring.watchshop.entity.CustomUserDetail;
import com.seuit.spring.watchshop.entity.User;
import com.seuit.spring.watchshop.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/duyanh/create")
	@ResponseBody
	public void createAdmin(@RequestBody User user) {
         userService.addUser(user, "admin");
	}
	
	@GetMapping(value = { "/" })
	public String showIndex() {
		return "client/index";
	}

	@GetMapping(value = { "/search" })
	public String showSearchPage() {
		return "client/products";
	}

	@GetMapping(value = { "/citizen-watches" })
	public String showCitizen() {
		return "client/products";
	}

	@GetMapping(value = { "/ogival-watches" })
	public String showOgival() {
		return "client/products";
	}


	@GetMapping(value = { "/orient-watches" })
	public String showOrient() {
		return "client/products";
	}


	@GetMapping(value = { "/bulova-watches" })
	public String showBulova() {
		return "client/products";
	}
	
	@GetMapping(value = { "/product-details" })
	public String showProductDetails() {
		return "client/product_details";
	}

	@GetMapping(value = { "/cart" })
	public String showCart() {
		return "client/cart";
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
