package com.seuit.spring.watchshop.service;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seuit.spring.watchshop.entity.Cart;
import com.seuit.spring.watchshop.entity.CartDetail;
import com.seuit.spring.watchshop.entity.Customer;
import com.seuit.spring.watchshop.entity.Order;
import com.seuit.spring.watchshop.entity.OrderDetail;
import com.seuit.spring.watchshop.entity.OrderStatus;
import com.seuit.spring.watchshop.entity.Payment;
import com.seuit.spring.watchshop.repository.CustomerRepository;
import com.seuit.spring.watchshop.repository.OrderRepository;
import com.seuit.spring.watchshop.repository.OrderStatusRepository;
import com.seuit.spring.watchshop.repository.PaymentRepository;

import javassist.NotFoundException;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerService customerService;
	
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderStatusRepository orderStatusRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	
	@Override
	@Transactional
	public boolean createOrder(Integer idPayment) {
		boolean result = true;
		Customer customer = null;
		Cart cart = null;
		Optional<Payment> payment = null;
		Optional<OrderStatus> orderStatus = null;
		Order order = new Order();
		Set<CartDetail> cartDetails = null;
		Integer idCustomer = customerService.getIdCustomerByPrincipal();
		if (idCustomer == null) {
			result = false;
		}else {
			try {
				customer = customerService.getInforMe();
				payment = paymentRepository.findById(idPayment);
				//get order status = 1 : 'unconfirmed'
				orderStatus = orderStatusRepository.findById(1);
				cart = customer.getCart();
				cartDetails = cart.getCartDetails();
				
				order.setCustomerO(customer);
				order.setOrderStatusO(orderStatus.get());
				order.setPaymentO(payment.get());
				order.setPrice(cart.getPrice());
				
				cartDetails.forEach(c->{
					OrderDetail orderDetail = new OrderDetail(order,c.getProduct(),c.getAmount());
					order.getOrderDetails().add(orderDetail);
				});
				
				System.out.println("begin");
				System.out.println(order.getPrice());
				System.out.println(order.getOrderStatusO().getOrderStatus());
				System.out.println(order.getCustomerO().getName());
				System.out.println(order.getPaymentO().getName());
				
				orderRepository.save(order);
				
			}catch (Exception e) {
				// TODO: handle exception
				result = false;
			}
		}
		return result;
	}


	@Override
	@Transactional
	public List<Order> listOrder() {
		return orderRepository.findAll();
	}


	@Override
	@Transactional
	public Set<OrderDetail> listOrderDetailByIdOrder(Integer idOrder) {
		// TODO Auto-generated method stub
		Optional<Order> order = null;
		try {
			order = orderRepository.findById(idOrder);
			return order.get().getOrderDetails();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	
	
	
	

}
