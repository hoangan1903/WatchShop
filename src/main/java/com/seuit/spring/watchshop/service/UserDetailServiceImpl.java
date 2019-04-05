package com.seuit.spring.watchshop.service;

import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.CustomUserDetail;
import com.seuit.spring.watchshop.entity.User;
import com.seuit.spring.watchshop.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userOptional = userRepository.findByUsername(username);
		userOptional.orElseThrow(() -> new UsernameNotFoundException("cant find user"));
		logger.info(userOptional.get().getPassword());
		userOptional.get().getRoles().stream().forEach((role)->logger.info(role.getName()));
		return userOptional.map((user)->new CustomUserDetail(user)).get();
	}

}
