package com.seuit.spring.watchshop.service;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.seuit.spring.watchshop.entity.CustomUserDetail;
import com.seuit.spring.watchshop.entity.PasswordResetToken;
import com.seuit.spring.watchshop.entity.Role;
import com.seuit.spring.watchshop.repository.PasswordTokenRepository;
import com.seuit.spring.watchshop.repository.RoleRepository;

import org.apache.poi.util.SystemOutLogger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.seuit.spring.watchshop.entity.User;
import com.seuit.spring.watchshop.helper.DAHelper;
import com.seuit.spring.watchshop.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MailSender mailSender;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PasswordTokenRepository passwordTokenRepository;

	@Autowired
	private RoleRepository roleRepository;

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
	public Integer addUser(User user, String roleName) {
		
		String subject=env.getProperty("email.createAccount.headerName");
		String body = env.getProperty("email.createAccount.messager");
		
		// Mã hoá mật khẩu user
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Integer roleId = findRoleIdByRoleName(roleName);

		// Mở phiên @Transactional
		Optional<User> userTemp = userRepository.findByUsername(user.getUsername());
		if (userTemp.isPresent()) {
			return 0;
		} else {

			DAHelper.getInstance().createLoggerMessager(this.getClass().getName(), user.getEmail());
			// Note : @ManyToMany bidirectional

			// Tạo Session để kết nối tới database
			Session session = this.getSession();

			// user trước khi thêm đang ở trạng thái Transient của hibernate Sau khi mở
			// session

			// Quan trọng : Khi lưu user vào session
			// Sẽ thực hiện Persist user -> chuyển trạng thái của user vào persistence
			// context = flush
			if (user.getId() == null) {
				// Tạo truy vấn sql để lấy role với id = 2 ( manager )
				String sql = "FROM Role r WHERE r.id=:id";
				Query query = session.createQuery(sql);
				query.setParameter("id", roleId);
				Role role = (Role) query.getSingleResult();

				// Khởi tạo HashSet
				Set<Role> roles = new HashSet<>();

				// Thêm vai trò vào HashSet
				roles.add(role);

				// Set một list HashSet vai trò cho user .
				user.setRoles(roles);
				session.persist(user);
			} else {

				User userOld = session.get(User.class, user.getId());
				userOld.setUsername(user.getUsername());
				userOld.setEmail(user.getEmail());
				userOld.setPassword(user.getPassword());

				session.merge(userOld);
			}
			mailSender.send(DAHelper.getInstance().constructEmail(subject,body,user));
			return 1;
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
		user.orElseThrow(() -> new UsernameNotFoundException("Cann't find user"));
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
		Query query = session.createQuery(sql).setFirstResult(page * size).setMaxResults(size);
		return query.getResultList();
	}

	@Override
	@Transactional
	public Long countUser() {
		Session session = getSession();
		String sqlCount = "SELECT count(u.id) FROM User u";
		Query queryCount = session.createQuery(sqlCount);
		Long count = (Long) queryCount.getSingleResult();
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
		query.setParameter("username", "%" + keyword + "%");
		return query.getResultList();
	}

	@Override
	@Transactional
	public User getMe() {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();
		return user;
	}

	@Override
	@Transactional
	public Integer isLoggedIn() {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getPrincipal());
		if (auth.getPrincipal() != "anonymousUser") {
			User user = (CustomUserDetail) auth.getPrincipal();
			Set<String> list = user.getRoles().stream().map((r) -> r.getName()).collect(Collectors.toSet());
			for (String string : list) {
				System.out.println(string);
				if (string.equals("customer")) {
					return 1;
				}
			}
			return 0;
		}
		return 0;
	}

	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username).get();
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordTokenRepository.save(myToken);
	}
	

	@Override
	public String validatePasswordResetToken(long id, String token) {
		PasswordResetToken passToken = passwordTokenRepository.findByToken(token);

		if ((passToken == null) || (passToken.getUser().getId() != id)) {
			return "invalidToken";
		}

		java.util.Date date = new java.util.Date();
		if ((passToken.getExpiryDate().getTime() - date.getTime()) <= 0) {
			return "expired";
		}

		User user = passToken.getUser();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null,
				Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext().setAuthentication(auth);
		return null;
	}
	
	@Override
	@Transactional
	public User getManagerById(Integer id) {
		return userRepository.getOne(id);
	}

	@Override
	@Transactional
	public void changeUserPassword(User user, String password) {
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
		
	}

	@Override
	public void resetPassword(HttpServletRequest request, String username) throws NotFoundException {
		String subject = env.getProperty("email.ResetMall.headerName");
		String mess = env.getProperty("email.ResetMall.messager");
		String body;
		User user = this.findUserByUsername(username);
	    if (user == null) {	
	        throw new NotFoundException("cant find user");
	    }
	    String token = UUID.randomUUID().toString();
	    this.createPasswordResetTokenForUser(user, token);
	    String url = request.getContextPath() + "/user/changePassword?id=" + 
			     user.getId() + "&token=" + token;
	    body=mess+"\r\n"+url;
	    mailSender.send(DAHelper.getInstance().constructEmail(subject,body,user));
	}

	@Override
	public void savePasswordAfterChanged(HttpServletRequest request, String newPassword) {
		 String subject = env.getProperty("email.ResetPassToSuccess.headerName");
		    String body = env.getProperty("email.ResetPassToSuccess.messager");
			User user = 
		      (User) SecurityContextHolder.getContext()
		                                  .getAuthentication().getPrincipal();
		    this.changeUserPassword(user, newPassword);
		    mailSender.send(DAHelper.getInstance().constructEmail(subject,body,user));
	}

	@Override
	@Transactional
	public void editManager(User user, Integer id) {
		Session session = this.getSession();
		User manager = session.get(User.class, id);
		manager.setEmail(user.getEmail());
		session.merge(manager);
	}
	
	

}
