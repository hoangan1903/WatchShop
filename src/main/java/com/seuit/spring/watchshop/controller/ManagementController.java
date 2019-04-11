package com.seuit.spring.watchshop.controller;

import com.seuit.spring.watchshop.repository.RoleRepository;
import com.seuit.spring.watchshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/manager")
public class ManagementController {


	private Logger logger = Logger.getLogger(this.getClass().getName());

	@GetMapping("/index")
	public String showIndexManagerMentPage() {
		return "manager/index";
	}

	@GetMapping(value="/CRUD_Products")
	public String showCRUDProductPage() {
		return "manager/CRUDProducts";
	}

	@GetMapping(value="/CRUD_Employees")
	public String showCRUDEmployeePage() {
		return "manager/CRUDEmployee";
	}

}
