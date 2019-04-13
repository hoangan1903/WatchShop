package com.seuit.spring.watchshop.service;
import com.seuit.spring.watchshop.entity.CustomerAPI;


public interface CustomerService {
	void saveOrUpdateCustomer(CustomerAPI customerApi,Integer id);
}
