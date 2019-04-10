package com.seuit.spring.watchshop.service;

import java.util.List;
import com.seuit.spring.watchshop.entity.User;

import javassist.NotFoundException;

public interface UserService {
	void deleteUserById(Integer id);

	void addUser(User user);

	List<User> getAllUser();

	User getUserById(Integer id) throws NotFoundException;
	
}
