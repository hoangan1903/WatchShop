package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Set;

import com.seuit.spring.watchshop.entity.User;

import javassist.NotFoundException;

public interface UserService {
	void deleteUserById(Integer id);

	Boolean addUser(User user, String roleName);

	List<User> getAllUser();

	User getUserById(Integer id) throws NotFoundException;

	Set<User> getAllEmployee();

	List<User> findPaginated(Integer page, Integer size);

	Long countUser();

	List<User> getListUserByKeyword(String keyword);
	
	User getMe();
	
	Integer isLoggedIn();

	User getManagerById(Integer id);
}
