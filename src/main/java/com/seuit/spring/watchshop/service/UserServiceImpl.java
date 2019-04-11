package com.seuit.spring.watchshop.service;

import java.util.*;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.seuit.spring.watchshop.entity.Role;
import com.seuit.spring.watchshop.repository.RoleRepository;
import org.hibernate.Session;
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
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

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
		//Mở phiên @Transactional
		Optional<User> userTemp = userRepository.findByUsername(user.getUsername());
		if(userTemp.isPresent()) {
			return;
		}else {

			//Note : @ManyToMany bidirectional

			//Tạo Session để kết nối tới database
			Session session = this.getSession();

			//user trước khi thêm đang ở trạng thái Transient của hibernate Sau khi mở session

			//Tạo truy vấn sql để lấy role với id = 2 ( manager )
			String sql = "FROM Role r WHERE r.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", 2);
			Role role = (Role) query.getSingleResult();

			//Khởi tạo HashSet
			Set<Role> roles = new HashSet<>();

			// Thêm vai trò vào HashSet
			roles.add(role);

			//Set một list HashSet vai trò cho user .
			user.setRoles(roles);

			//Mã hoá mật khẩu user
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			//Quan trọng : Khi lưu user vào session
			// Sẽ thực hiện Persist user -> chuyển trạng thái của user vào persistence context = flush
			session.persist(user);
			// Đóng phiên @Transactional sẽ lưu user vào database = commit
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
