package com.seuit.spring.watchshop.service;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seuit.spring.watchshop.entity.Customer;
import com.seuit.spring.watchshop.entity.CustomerAPI;
import com.seuit.spring.watchshop.entity.User;
import com.seuit.spring.watchshop.repository.CustomerRepository;

import javassist.NotFoundException;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional
	public void saveOrUpdateCustomer(CustomerAPI customerApi, Integer id) {
		// TODO Auto-generated method stub
		if(id==null){
            User user = new User();
            Customer customer = new Customer();
            setUserAndCustomer(customerApi, user, customer);
            user.setCustomer(customer);
            customer.setUser(user);
            userService.addUser(user,"customer");
        }else{
            try {
            	
                Optional<Customer> customerPersis = customerRepository.findById(id);
                customerPersis.orElseThrow(()->new NotFoundException("Cant find customer"));
                if(customerPersis.isPresent()==true){
                    User userPersis = customerPersis.get().getUser();
                    setUserAndCustomer(customerApi,userPersis,customerPersis.get());
                    userService.addUser(userPersis,"customer");
                }

            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
	}
	 private void setUserAndCustomer(CustomerAPI customerApi, User user, Customer customer) {
	        user.setUsername(customerApi.getUser().getUsername());
	        user.setPassword(customerApi.getUser().getPassword());
	        user.setEmail(customerApi.getUser().getEmail());
	        customer.setName(customerApi.getCustomer().getName());
	        customer.setPhone(customerApi.getCustomer().getPhone());
	        customer.setAddress(customerApi.getCustomer().getAddress());
	    }
	

}
