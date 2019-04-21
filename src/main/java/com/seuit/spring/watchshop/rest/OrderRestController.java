package com.seuit.spring.watchshop.rest;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.entity.Order;
import com.seuit.spring.watchshop.entity.OrderDetail;
import com.seuit.spring.watchshop.service.OrderService;

@RestController
@RequestMapping(value="/rest")
public class OrderRestController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/order/{id}")
	private String createOrder(@PathVariable(name = "id") Integer idPayment) {
		boolean boo = orderService.createOrder(idPayment);
		if(boo==true) {
			return "ok";
		}else {
			return "false";
		}
	}
	@GetMapping("/order")
	private List<Order> listAllOrder() {
		return orderService.listOrder();
		
	}
	
	@GetMapping("/order/detail/{id}")
	private Set<OrderDetail> getOrderDetailByIdOrder(@PathVariable(name="id") Integer idOrder) {
		return orderService.listOrderDetailByIdOrder(idOrder);
	}
}
