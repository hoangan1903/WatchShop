package com.seuit.spring.watchshop.service;
import com.seuit.spring.watchshop.entity.CustomerAPI;


public interface CustomerService {
	Boolean saveOrUpdateCustomer(CustomerAPI customerApi,Integer id);
}
