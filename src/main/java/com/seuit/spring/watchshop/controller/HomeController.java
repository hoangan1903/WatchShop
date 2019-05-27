package com.seuit.spring.watchshop.controller;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.seuit.spring.watchshop.entity.User;
import com.seuit.spring.watchshop.service.UserService;


import javassist.NotFoundException;

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

	@GetMapping(value = { "/checkout" })
	public String showCheckout() {
		return "client/checkout";
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
	
	@GetMapping("/forgotPassword")
	public String showResetPage() {
		return "forgotPassword";
	}
	
	@GetMapping("/updatePassword")
	public String showUpdatePassPage() {
		return "updatePassword";
	}
	
	@PostMapping("/user/resetPassword")
	@ResponseBody
	public void resetPassword(HttpServletRequest request,@RequestParam("username") String username) throws NotFoundException {
		userService.resetPassword(request, username);
	}
	@GetMapping("/user/changePassword")
	public String showChangePasswordPage(@RequestParam("id") long id, @RequestParam("token") String token,RedirectAttributes redirect) {
	    String result = userService.validatePasswordResetToken(id, token);
	    if (result != null) {
	        return "redirect:/login";
	    }
	    return "redirect:/updatePassword";
	}
	@PostMapping("/user/savePassword")
	@ResponseBody
	public void savePassword(HttpServletRequest request,@RequestParam(name="newPassword") String newPassword) {
	   userService.savePasswordAfterChanged(request, newPassword);
	}
}
