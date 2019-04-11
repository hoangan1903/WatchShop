package com.seuit.spring.watchshop.controller;

import com.seuit.spring.watchshop.entity.User;
import com.seuit.spring.watchshop.repository.RoleRepository;
import com.seuit.spring.watchshop.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/manager")
public class ManagementController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;


	private Logger logger = Logger.getLogger(this.getClass().getName());

	@GetMapping("/index")
	public String showIndexManagerMentPage() {
		return "manager/index";
	}

	@GetMapping(value="/CRUD_Products")
	public String showCRUDProductPage() {
		return "manager/CRUDProducts";
	}

	@GetMapping(value = "/CRUD_Employees")
	public String showCRUDEmployeePage(Model model) {
		Set<User> listUser = userService.getAllEmployee();
		model.addAttribute("listUser", listUser);
		model.addAttribute("user", new User());
		return "manager/CRUDEmployee";
	}


	@GetMapping("/CRUD_Employees/addUser")
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "manager/userForm";
	}

	@PostMapping("/CRUD_Employees/saveUser")
	public String saveUser(@ModelAttribute("user") User user, RedirectAttributes redirect) {
		userService.addUser(user, "employee");
		return "redirect:/manager/CRUD_Employees";
	}

	@GetMapping("/CRUD_Employees/deleteUser/{userId}")
	public String deleteUser(@PathVariable(value = "userId") Integer userId, RedirectAttributes redirect) {
		userService.deleteUserById(userId);
		return "redirect:/manager/CRUD_Employees";
	}

	@GetMapping("/CRUD_Employees/edit/{userId}")
	public String editUsser(@PathVariable(value = "userId") Integer userId, RedirectAttributes redirect, Model model) {
		try {
			model.addAttribute("user", userService.getUserById(userId));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "manager/userForm";
	}
}
