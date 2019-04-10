package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.seuit.spring.watchshop.entity.User;

import com.seuit.spring.watchshop.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	@Transactional
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void addUser(User user) {
		Optional<User> userTemp = userRepository.findByUsername(user.getUsername());
		if(userTemp.isPresent()) {
			return;
		}else {
			user.getRoles().stream().forEach((role)->{
				logger.info(role.getName().toString());//admin
			});
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public User getUserById(Integer id) throws NotFoundException {
		Optional<User> user = userRepository.findById(id);
		user.orElseThrow(()-> new UsernameNotFoundException("Cann't find user"));
		return user.get();
	}
}
