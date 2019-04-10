package com.seuit.spring.watchshop.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import com.seuit.spring.watchshop.entity.Role;
import com.seuit.spring.watchshop.repository.RoleRepository;
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

	@Autowired
	private RoleRepository roleRepository;
	
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
			//2 = manager

			// Lỗi logic :

			//NOTE : Nên setUsers của Role
			// NOTE : Không được setRoles của users

			////////////////Lý do Nên setUsers của Role ://////////////////

			// Khi xoá tất cả User có cùng Role thì sẽ không ảnh hưỡng đến Role trong database
			// ( Role Tồn tại để thêm User mới )

			// Khi xoá tất cả Role của cùng user đó thì sẽ xoá luôn user đó
			// (Không có Role : Không có lý do để user tồn tại )


			Role role = roleRepository.findById(2).get();
			Set<User> users = new HashSet<>();
			users.add(user);

			role.setUsers(users);

			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
		}
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
