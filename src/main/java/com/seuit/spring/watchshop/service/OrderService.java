package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Set;

import com.seuit.spring.watchshop.entity.Order;
import com.seuit.spring.watchshop.entity.OrderDetail;

public interface OrderService {
	public boolean createOrder(Integer idPayment);
	public List<Order> listOrder();
	public Set<OrderDetail> listOrderDetailByIdOrder(Integer idOrder);
}
