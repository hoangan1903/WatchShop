package com.seuit.spring.watchshop.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.entity.User;
import com.seuit.spring.watchshop.service.UserService;

@RestController
@RequestMapping(value= {"/rest"})
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value="/users", params= {"page", "size"})
	List<User> findAllUserByPaginated(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
		return userService.findPaginated(page, size);
	}
	
	@GetMapping("/users/count")
	Long getCountTototalUser() {
		return userService.countUser();
	}
	
	@GetMapping("/users/find/{keyword}")
	List<User> findProductByKeyword(@PathVariable(value="keyword") String keyword){
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
}
