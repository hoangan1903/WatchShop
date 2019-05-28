package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Optional;

import com.seuit.spring.watchshop.entity.Customer;
import com.seuit.spring.watchshop.entity.CustomerAPI;
import com.seuit.spring.watchshop.entity.Order;

public interface CustomerService {
	Integer saveOrUpdateCustomer(CustomerAPI customerApi, Integer id);

	public Integer getIdCustomerByPrincipal();

	public Customer getInforMe();

	List<Customer> getAllCustomers();
	
	List<Customer> findPaginated(Integer page, Integer size);
	
	Long countCustomer();
	
	List<Customer> getListCustomerByKeyword(String keyword);
	
	Optional<Customer> getCustomerById(Integer id);
	
	List<Order> getCustomerOrders();
	
	public Integer updateCustomer(Customer customer);
}
