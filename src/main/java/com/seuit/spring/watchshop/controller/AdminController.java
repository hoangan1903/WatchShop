package com.seuit.spring.watchshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seuit.spring.watchshop.entity.Role;
import com.seuit.spring.watchshop.entity.User;
import com.seuit.spring.watchshop.service.UserService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private UserService userService;

	@GetMapping("/CRUD_User")
	public String showCRUDUserPage(Model model) {
		List<User> listUser = userService.getAllUser();
		model.addAttribute("listUser", listUser);
		model.addAttribute("user", new User());
		return "admin/CRUDUsers";
	}
	
	@GetMapping("/CRUD_User/addUser")
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "admin/userForm";
	}
	
	@PostMapping("/CRUD_User/saveUser")
	public String saveUser(@ModelAttribute("user") User user, RedirectAttributes redirect) {
		userService.addUser(user);
		return "redirect:/admin/CRUD_User";
	}

	@GetMapping("/CRUD_User/deleteUser/{userId}")
	public String deleteUser(@PathVariable(value = "userId") Integer userId, RedirectAttributes redirect) {
		userService.deleteUserById(userId);
		return "redirect:/admin/CRUD_User";
	}

	@GetMapping("/CRUD_User/edit/{userId}")
	public String editUsser(@PathVariable(value = "userId") Integer userId, RedirectAttributes redirect, Model model){
		try {
			model.addAttribute("user", userService.getUserById(userId));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/userForm";
	}
}
