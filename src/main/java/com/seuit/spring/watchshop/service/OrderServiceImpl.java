package com.seuit.spring.watchshop.service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seuit.spring.watchshop.entity.Cart;
import com.seuit.spring.watchshop.entity.CartDetail;
import com.seuit.spring.watchshop.entity.Customer;
import com.seuit.spring.watchshop.entity.Order;
import com.seuit.spring.watchshop.entity.OrderDetail;
import com.seuit.spring.watchshop.entity.OrderStatus;
import com.seuit.spring.watchshop.entity.Payment;
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
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

	
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
	public List<Order> listOrder(Integer pageId,Integer size,Integer orderStatus,Integer orderCreatedStatus,Integer orderPriceStatus,Integer orderPaymentStatus) {
		List<Order> listOrder = orderRepository.findAll();
		if(orderStatus!=0) {
			listOrder = listOrder.stream().filter((o)->o.getOrderStatusO().getId()==orderStatus).collect(Collectors.toList());
		}
		if(orderCreatedStatus!=0) {
			if(orderCreatedStatus==1) {
				listOrder = listOrder.stream().sorted(Comparator.comparing(Order::getCreateAt)).collect(Collectors.toList());
			}else {
				//create status = 2
				listOrder = listOrder.stream().sorted(Comparator.comparing(Order::getCreateAt).reversed()).collect(Collectors.toList());
			}
		}
		if(orderPriceStatus!=0) {
			if(orderPriceStatus==1) {
				listOrder = listOrder.stream().sorted(Comparator.comparing(Order::getPrice).reversed()).collect(Collectors.toList());
			}else {
				//price status = 2
				listOrder = listOrder.stream().sorted(Comparator.comparing(Order::getPrice)).collect(Collectors.toList());
			}
		}
		if(orderPaymentStatus!=0) {
			if(orderPaymentStatus==1) {
				listOrder = listOrder.stream().sorted(Comparator.comparing(Order::getOrderPaymentId).reversed()).collect(Collectors.toList());
			}else {
				//payment status = 2
				listOrder = listOrder.stream().sorted(Comparator.comparing(Order::getOrderPaymentId)).collect(Collectors.toList());
			}
		}
		if(size!=0) {
			listOrder =  this.findPagination(pageId, size,listOrder);
		}
		return listOrder;
	}


	@Override
	@Transactional
	public Map<String, Object> listOrderDetailByIdOrder(Integer idOrder) {
		// TODO Auto-generated method stub
		Optional<Order> order = null;
		Map<String, Object> objects = new HashMap<String, Object>();
		try {
			order = orderRepository.findById(idOrder);
			objects.put("products", order.get().getOrderDetails()) ;
			objects.put("customer", order.get().getCustomerO());
			return objects;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}


	@Override
	@Transactional
	public String updateStatusOrder(Integer idOrder,boolean isSuccess) throws NotFoundException {
		// TODO Auto-generated method stub
		Optional<Order> order = orderRepository.findById(idOrder);
		OrderStatus orderStatusAfterUpdate = this.chooseOrderStatusForUpdate(order.get(), isSuccess);
		order.get().setOrderStatusO(orderStatusAfterUpdate);
		return order.get().getOrderStatusO().getOrderStatus();
	}
	
	@Transactional
	private OrderStatus chooseOrderStatusForUpdate(Order order,boolean isSuccess) {
		if(order.getOrderStatusO().getId()==1) {
			return orderStatusRepository.findById(2).get();
		}else {
			if(order.getOrderStatusO().getId()==2) {
					if(isSuccess==true) {
					return orderStatusRepository.findById(3).get();
				}else {
					return orderStatusRepository.findById(4).get();
				}
			}else {
				return orderStatusRepository.findById(order.getOrderStatusO().getId()).get();
			}
		}
	}

	@Override
	@Transactional
	public List<Order> findPagination(Integer pageId, Integer size,List<Order> listOrder) {
		Integer start = pageId*size;
		return listOrder.stream().filter((c)->listOrder.indexOf(c)>=start).limit(size).collect(Collectors.toList());
	}


	@Override
	@Transactional
	public Integer getCountAllOrder() {
		// TODO Auto-generated method stub
		return orderRepository.findAll().size();
	}


	@Override
	@Transactional
	public List<Order> listOrderByCustomerId(Integer idCustomer) {
		Customer customer = customerService.getCustomerById(idCustomer).get();
		return customer.getOrders();	
	}
}
