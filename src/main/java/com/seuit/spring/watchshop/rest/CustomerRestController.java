package com.seuit.spring.watchshop.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.entity.Customer;
import com.seuit.spring.watchshop.entity.CustomerAPI;
import com.seuit.spring.watchshop.entity.Order;
import com.seuit.spring.watchshop.service.CartService;
import com.seuit.spring.watchshop.service.CustomerService;
import com.seuit.spring.watchshop.service.OrderService;

@RestController
@RequestMapping(value= {"/rest"})
public class CustomerRestController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/customers")
    private String newCustomer(@Valid @RequestBody CustomerAPI customerApi) {
		boolean boo =  customerService.saveOrUpdateCustomer(customerApi, null);
        if(boo==true) {
        	return "success";
        }else {
        	return "fail";
        }
    }
	@PutMapping("/customers/{id}")
    private String updateCustomer(@Valid @RequestBody CustomerAPI customerAPI, BindingResult result, @PathVariable(value = "id") @Min(1) Integer id) {
        customerService.saveOrUpdateCustomer(customerAPI,id);
        return "Update Success";
    }
	
	@GetMapping("/me")
	private Customer getInforMe() {
		return customerService.getInforMe();
	}
	
	@GetMapping(value = "/customers", params = { "page", "size" })
	List<Customer> findAllCustomerByPaginated(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		return customerService.findPaginated(page, size);
	}

	@GetMapping("/customers/count")
	Long getCountTotalCustomer() {
		return customerService.countCustomer();
	}

	@GetMapping("/customers/find/{keyword}")
	List<Customer> findCustomerByKeyword(@PathVariable(value = "keyword") String keyword) {
		return customerService.getListCustomerByKeyword(keyword);
	}

	@GetMapping("/customers/detail/{id}")
	List<Order> showAllOrderByCustomerId(@PathVariable(value = "id") Integer id) {
		return orderService.listOrderByCustomerId(id);
	}
}
