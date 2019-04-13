package com.seuit.spring.watchshop.rest;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.entity.CustomerAPI;
import com.seuit.spring.watchshop.entity.EmployeeApi;
import com.seuit.spring.watchshop.service.CustomerService;

@RestController
@RequestMapping(value= {"/rest"})
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customers")
    String newCustomer(@Valid @RequestBody CustomerAPI customerApi) {
		customerService.saveOrUpdateCustomer(customerApi, null);
        return "Add success";
    }
	@PutMapping("/customers/{id}")
    String updateCustomer(@Valid @RequestBody CustomerAPI customerAPI, BindingResult result, @PathVariable(value = "id") @Min(1) Integer id) {
        customerService.saveOrUpdateCustomer(customerAPI,id);
        return "Update Success";
    }
}
