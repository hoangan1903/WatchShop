package com.seuit.spring.watchshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.seuit.spring.watchshop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seuit.spring.watchshop.entity.Customer;
import com.seuit.spring.watchshop.entity.ProductApi;
import com.seuit.spring.watchshop.entity.Role;
import com.seuit.spring.watchshop.entity.User;
import com.seuit.spring.watchshop.helper.DAHelper;
import com.seuit.spring.watchshop.helper.ProductExcelHelper;
import com.seuit.spring.watchshop.service.CustomerService;
import com.seuit.spring.watchshop.service.DBFileStorageService;
import com.seuit.spring.watchshop.service.ProductService;
import com.seuit.spring.watchshop.service.UserService;

import javassist.NotFoundException;

import javax.transaction.Transactional;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private CustomerService customerService;
	//ADMIN AREA
	
	@GetMapping("/CRUD_Alert")
	public String showAlert() {
		return "admin/CRUDAlert";
	}
	
	@GetMapping("/CRUD_User")
	public String showCRUDUserPage(Model model) {
		List<User> listUser = userService.getAllUser();
		model.addAttribute("listUser", listUser);
		model.addAttribute("user", new User());
		return "admin/CRUDUsers";
	}
	
	//MANAGEMENT AREA

	@GetMapping(value = "/CRUD_Products")
	public String showCRUDProductPage() {
		return "admin/CRUDProducts";
	}

	@GetMapping(value = "/CRUD_Employees")
	public String showCRUDEmployeePage() {
		return "admin/CRUDEmployee";
	}

	@PostMapping(value = "/CRUD_Products/importFromExcel")
	public String importExcel(@RequestParam("file") MultipartFile file, RedirectAttributes redirect) {
		ProductExcelHelper productExcelHelper = new ProductExcelHelper();
		File excelFile = DAHelper.getInstance().convert(file);
		List<ProductApi> productApis;
		try {
			productApis = productExcelHelper.saveProductsFromExcelFile(excelFile);
			productApis.stream().forEach((productApi) -> {
				productService.saveOrUpdate(productApi, null);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/admin/CRUD_Products";
	}
	
	@GetMapping("/list_Customers")
	public String showAllCustomer(Model model) {
		List<Customer> listCustomeres = customerService.getAllCustomers();

		model.addAttribute("listCustomers", listCustomeres);
		model.addAttribute("customer", new Customer());
		return "admin/listCustomers";
	}
	
	@GetMapping("/order")
	public String test() {
		return "admin/Order";
	}
	
	@GetMapping("/CRUD_OtherFunction")
	public String showCRUD_OtherFunction() {
		return "admin/CRUD_OtherFunction";
	}
	
	@GetMapping("/report")
	public String report() {
		return "admin/report";
	}
	
	@GetMapping("/feedback")
	public String feedback() {
		return "admin/Feedback";
	}
}
