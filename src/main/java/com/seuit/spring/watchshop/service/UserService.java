package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Set;

import com.seuit.spring.watchshop.entity.User;

import javassist.NotFoundException;

public interface UserService {
	void deleteUserById(Integer id);

    void addUser(User user, String roleName);

	List<User> getAllUser();

	User getUserById(Integer id) throws NotFoundException;

    Set<User> getAllEmployee();
	
}
