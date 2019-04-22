package com.seuit.spring.watchshop.service;

import java.util.*;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.seuit.spring.watchshop.entity.Role;
import com.seuit.spring.watchshop.repository.RoleRepository;
import org.hibernate.Criteria;
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

	private Integer findRoleIdByRoleName(String roleName) {
		Integer roleId = null;
		switch (roleName) {
			case "admin":
				roleId = 1;
				break;
			case "manager":
				roleId = 2;
				break;
			case "employee":
				roleId = 3;
				break;
			case "customer":
				roleId = 4;
				break;
		}
		return roleId;
	}
	
	@Override
	@Transactional
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Boolean addUser(User user, String roleName) {
		//Mã hoá mật khẩu user
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Integer roleId = findRoleIdByRoleName(roleName);

		//Mở phiên @Transactional
		Optional<User> userTemp = userRepository.findByUsername(user.getUsername());
		if(userTemp.isPresent()) {
			return false;
		}else {
			
			logger.info(user.getEmail());
			//Note : @ManyToMany bidirectional

			//Tạo Session để kết nối tới database
			Session session = this.getSession();

			//user trước khi thêm đang ở trạng thái Transient của hibernate Sau khi mở session
			
			
			
			//Quan trọng : Khi lưu user vào session
			// Sẽ thực hiện Persist user -> chuyển trạng thái của user vào persistence context = flush
			if (user.getId() == null) {
				//Tạo truy vấn sql để lấy role với id = 2 ( manager )
				String sql = "FROM Role r WHERE r.id=:id";
				Query query = session.createQuery(sql);
				query.setParameter("id", roleId);
				Role role = (Role) query.getSingleResult();

				//Khởi tạo HashSet
				Set<Role> roles = new HashSet<>();

				// Thêm vai trò vào HashSet
				roles.add(role);

				//Set một list HashSet vai trò cho user .
				user.setRoles(roles);
				session.persist(user);
			} else {

				User userOld = session.get(User.class, user.getId());
				userOld.setUsername(user.getUsername());
				userOld.setEmail(user.getEmail());
				userOld.setPassword(user.getPassword());
				
				session.merge(userOld);
			}
			return true;
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

	@Override
	@Transactional
	public Set<User> getAllEmployee() {
		Session session = this.getSession();
		Role role = roleRepository.findById(findRoleIdByRoleName("employee")).get();
		return role.getUsers();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> findPaginated(Integer page, Integer size) {
		Session session = getSession();
		String sql = "FROM User";
		Query query = session.createQuery(sql).setFirstResult(page*size).setMaxResults(size);
		return query.getResultList();
	}

	@Override
	@Transactional
	public Long countUser() {
		Session session =getSession();
		String sqlCount = "SELECT count(u.id) FROM User u";
		Query queryCount = session.createQuery(sqlCount);
		Long count = (Long)queryCount.getSingleResult();
		System.out.println(count.toString());
		return count;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<User> getListUserByKeyword(String keyword) {
		Session session = getSession();
		String sql = "SELECT u FROM User u WHERE u.username like:username";
		javax.persistence.Query query = session.createQuery(sql).setMaxResults(10);
		query.setParameter("username", "%" +keyword + "%");
		return query.getResultList();
	}

}
