package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Cart;
import com.seuit.spring.watchshop.entity.CustomUserDetail;
import com.seuit.spring.watchshop.entity.Customer;
import com.seuit.spring.watchshop.entity.CustomerAPI;
import com.seuit.spring.watchshop.entity.Order;
import com.seuit.spring.watchshop.entity.User;
import com.seuit.spring.watchshop.repository.CustomerRepository;
import com.seuit.spring.watchshop.repository.OrderRepository;

import javassist.NotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	@Transactional
	public Integer saveOrUpdateCustomer(CustomerAPI customerApi, Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
			User user = new User();
			Customer customer = new Customer();
			setUserAndCustomer(customerApi, user, customer);
			user.setCustomer(customer);
			customer.setUser(user);

			// create cart for customer
			Cart cart = new Cart();
			cart.setCustomer(customer);
			customer.setCart(cart);
			cart.setPrice((double) 0);

			return checkAddUser(user);
		} else {
			try {

				Optional<Customer> customerPersis = customerRepository.findById(id);
				customerPersis.orElseThrow(() -> new NotFoundException("Cant find customer"));
				if (customerPersis.isPresent() == true) {
					User userPersis = customerPersis.get().getUser();
					setUserAndCustomer(customerApi, userPersis, customerPersis.get());
					return checkAddUser(userPersis);
				}

			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	private Integer checkAddUser(User user) {
		return userService.addUser(user, "customer");
	}

	private void setUserAndCustomer(CustomerAPI customerApi, User user, Customer customer) {
		user.setUsername(customerApi.getUser().getUsername());
		user.setPassword(customerApi.getUser().getPassword());
		user.setEmail(customerApi.getUser().getEmail());
		customer.setName(customerApi.getCustomer().getName());
		customer.setPhone(customerApi.getCustomer().getPhone());
		customer.setAddress(customerApi.getCustomer().getAddress());
	}

	@Override
	@Transactional
	public Integer getIdCustomerByPrincipal() {
		// TODO Auto-generated method stub
		Integer result = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth);
		if (auth.getPrincipal() != "anonymousUser") {
			CustomUserDetail userDetails = (CustomUserDetail) auth.getPrincipal();
			try {
				User user = userService.getUserById(userDetails.getId());
				Customer customer = user.getCustomer();
				result = customer.getId();
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				result = null;
				e.printStackTrace();
			}
		} else {
			result = null;
		}
		return result;
	}

	@Override
	@Transactional
	public Customer getInforMe() {
		// TODO Auto-generated method stub
		Integer idCustomer = this.getIdCustomerByPrincipal();
		if (idCustomer == null) {
			return null;
		}
		return customerRepository.findById(idCustomer).get();
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		System.out.println(customers.get(0).getPhone());
		return customers;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Customer> findPaginated(Integer page, Integer size) {
		Session session = getSession();
		String sql = "FROM Customer";
		Query query = session.createQuery(sql).setFirstResult(page*size).setMaxResults(size);
		return query.getResultList();
	}

	@Override
	public Long countCustomer() {
		Session session = getSession();
		String sql = "SELECT count(c.id) FROM Customer c";
		Query query = session.createQuery(sql);
		Long count = (Long)query.getSingleResult();		
		return count;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Customer> getListCustomerByKeyword(String keyword) {
		Session session = getSession();
		String sql = "SELECT c FROM Customer c WHERE c.name like:name";
		javax.persistence.Query query = session.createQuery(sql).setMaxResults(10);
		query.setParameter("name","%" + keyword + "%");
		return query.getResultList();
	}

	@Transactional
	@Override
	public Optional<Customer> getCustomerById(Integer id) {
		return customerRepository.findById(id);
	}

	@Override
	public List<Order> getCustomerOrders() {
		Integer idCustomer = this.getIdCustomerByPrincipal();
		if (idCustomer == null) {
			return null;
		}
		Customer customer = this.getCustomerById(idCustomer).get();
		return customer.getOrders();
	}

	@Override
	@Transactional
	public Integer updateCustomer(Customer customer) {
		Session session = this.getSession();
		Integer idCustomer = this.getIdCustomerByPrincipal();
		if (idCustomer == null) {
			return 0;
		}
		Customer customerNew = session.get(Customer.class, idCustomer);
		customerNew.setName(customer.getName());
		customerNew.setAddress(customer.getAddress());
		customerNew.setPhone(customer.getPhone());
		session.merge(customerNew);
		return 1;
	}
}
