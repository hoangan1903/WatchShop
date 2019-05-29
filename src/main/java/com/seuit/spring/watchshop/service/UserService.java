package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.SimpleMailMessage;

import com.seuit.spring.watchshop.entity.User;

import javassist.NotFoundException;

public interface UserService {
	void deleteUserById(Integer id);

	Integer addUser(User user, String roleName);

	List<User> getAllUser();

	User getUserById(Integer id) throws NotFoundException;

	Set<User> getAllEmployee();

	List<User> findPaginated(Integer page, Integer size);

	Long countUser();

	List<User> getListUserByKeyword(String keyword);
	
	User getMe();
	
	Integer isLoggedIn();

	User getManagerById(Integer id);

	void resetPassword(HttpServletRequest request, String username) throws NotFoundException;

	void changeUserPassword(User user, String password);

	User findUserByUsername(String username);

	void createPasswordResetTokenForUser(User user, String token);

	void savePasswordAfterChanged(HttpServletRequest request, String newPassword);

	String validatePasswordResetToken(long id, String token);

	void editManager(User user, Integer id);
}
