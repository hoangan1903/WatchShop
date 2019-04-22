package com.seuit.spring.watchshop.service;

import java.util.List;

import com.seuit.spring.watchshop.entity.Customer;
import com.seuit.spring.watchshop.entity.CustomerAPI;

public interface CustomerService {
	Boolean saveOrUpdateCustomer(CustomerAPI customerApi, Integer id);

	public Integer getIdCustomerByPrincipal();

	public Customer getInforMe();

	List<Customer> getAllCustomers();
}
