package com.seuit.spring.watchshop.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.entity.User;
import com.seuit.spring.watchshop.service.UserService;

@RestController
@RequestMapping(value = { "/rest" })
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/users", params = { "page", "size" })
	List<User> findAllUserByPaginated(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		return userService.findPaginated(page, size);
	}

	@GetMapping("/users/count")
	Long getCountTototalUser() {
		return userService.countUser();
	}

	@GetMapping("/users/find/{keyword}")
	List<User> findProductByKeyword(@PathVariable(value = "keyword") String keyword) {
		return userService.getListUserByKeyword(keyword);
	}

	@GetMapping("/users/me")
	User getMe() {
		return userService.getMe();
	}

	@GetMapping("/users/isLoggedIn")
	Integer isLoggedIn() {
		return userService.isLoggedIn();
	}

	@DeleteMapping("/users/{id}")
	String deleteProduct(@PathVariable(value = "id") @Min(1) Integer id) {
		userService.deleteUserById(id);
		return "Delete Success";
	}

	@GetMapping("/users/{id}")
	User getManagerById(@PathVariable(value = "id") Integer id) {
		return userService.getManagerById(id);
	}

	@PostMapping("/users")
	String newManager(@Valid @RequestBody User user) {
		userService.addUser(user, "manager");
		return "Add success";
	}

	@PutMapping("/users/{id}")
	String editManager(@RequestBody User user, @PathVariable(value = "id") @Min(1) Integer id) {
		System.out.println("test edit: "+ user.getEmail());
		userService.editManager(user, id);
		return "Edit success";
	}

}
