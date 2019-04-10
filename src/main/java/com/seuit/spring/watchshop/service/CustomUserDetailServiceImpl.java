package com.seuit.spring.watchshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.seuit.spring.watchshop.entity.User;
import com.seuit.spring.watchshop.repository.UserRepository;

public class CustomUserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptioanl = userRepository.findByUsername(username);
		userOptioanl.orElseThrow(() -> new UsernameNotFoundException("Cant find user"));
		return null;
	}
}
