package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Map;
import com.seuit.spring.watchshop.entity.Order;
import com.seuit.spring.watchshop.entity.Product;

import javassist.NotFoundException;

public interface OrderService {
	public Integer createOrder(Integer idPayment);
	public List<Order> listOrder(Integer pageId,Integer size,Integer orderStatus,Integer orderCreatedStatus,Integer orderPriceStatus,Integer orderPaymentStatus);
	public Map<String, Object> listOrderDetailByIdOrder(Integer idOrder);
	public String updateStatusOrder(Integer idOrder,boolean isSuccess) throws NotFoundException;
	public List<Order> findPagination(Integer pageId,Integer size,List<Order> listOrder);
	public Integer getCountAllOrder();
	public List<Order> listOrderByCustomerId(Integer idCustomer);
	public Integer getCountNewOrder();
}
