package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.SimpleMailMessage;

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
	
	User findUserByUsername(String username);
	void createPasswordResetTokenForUser(User user, String token);
	SimpleMailMessage constructEmail(String subject, String body, User user);
	String validatePasswordResetToken(long id, String token);
	void changeUserPassword(User user, String password);
	void resetPassword(HttpServletRequest request,String username) throws NotFoundException;
	void savePasswordAfterChanged(HttpServletRequest request,String newPassword);
}
