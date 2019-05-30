package com.seuit.spring.watchshop.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
import com.seuit.spring.watchshop.entity.CartAPI;
import com.seuit.spring.watchshop.entity.CartDetail;
import com.seuit.spring.watchshop.entity.Customer;
import com.seuit.spring.watchshop.entity.Product;
import com.seuit.spring.watchshop.helper.DAHelper;
import com.seuit.spring.watchshop.repository.CustomerRepository;
import com.seuit.spring.watchshop.repository.ProductRepository;

import javassist.NotFoundException;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	@Transactional
	public Integer addProductToCart(CartAPI cartAPI) throws NotFoundException {
		Integer amount = cartAPI.getAmount();
		Integer idProduct = cartAPI.getIdProduct();
		Integer idCustomer = customerService.getIdCustomerByPrincipal();
		Integer productQuantityInDB = productService.getAvailableProduct(idProduct);
		Double totalPrice= 0D;
		Optional<Product> product = productRepository.findById(idProduct);
		Optional<Customer> customer = customerRepository.findById(idCustomer);
		// Kiểm tra xem số lượng sản phẩm trên giỏ hàng có lớn hơn số lượng sản phẩm còn
		// trong kho không ?
		// Kiểm tra xem khách hàng có tồn tại không ?
		// Kiểm tra xem
		if (idCustomer == null || product.isPresent() == false) {
			product.orElseThrow(() -> new NotFoundException("Cant find product with id :" + idProduct));
			customer.orElseThrow(() -> new NotFoundException("Cant find customer with id :" + idCustomer));
			return 0;
		}
		Cart cart = customer.get().getCart();
		CartDetail newCartDetail = new CartDetail(cart, product.get(), amount);
		if (amount > productQuantityInDB) {
			// Nếu số lượng thêm mới lớn hơn số lượng sản phẩm tồn tại trong kho
			return 0;
		} 
		if(cart.getCartDetails().isEmpty()==false)
		{
			// Nếu giỏ hàng không rỗng
			for (CartDetail cartDetailInDB : cart.getCartDetails()) {
				Integer idProductInCartDB = cartDetailInDB.getProduct().getId();
				if(idProductInCartDB.equals(idProduct)) {
					Integer productQuantityBeforeAdd = amount + cartDetailInDB.getAmount();
					//Nếu tồn tại sản phẩm trong giỏ hàng rồi
					if(productQuantityBeforeAdd > productQuantityInDB) {
						// Nếu số lượng thêm mới lớn hơn số lượng sản phẩm tồn tại trong kho
						return 0;
					}
					newCartDetail.setAmount(productQuantityBeforeAdd);
					break;
				}
			}
		}
		cart.getCartDetails().add(newCartDetail);
		for (CartDetail cartDetailInDB : cart.getCartDetails()) {
			totalPrice += cartDetailInDB.getSubtotal();
		}
		cart.setPrice(totalPrice);
		return 1;
	}

	@Override
	@Transactional
	public Integer upAmountProduct(Integer idProduct) {
		final Integer NUM_CHANGE = 1;
		Integer idCustomer = customerService.getIdCustomerByPrincipal();
		Customer customer = customerService.getCustomerById(idCustomer).isPresent()?customerService.getCustomerById(idCustomer).get():null;
		Cart cart = customer.getCart();
		if(customer == null || cart.getCartDetails().isEmpty()==true) {
			return 0;
		}
		Integer productQuantityInDB = productService.getAvailableProduct(idProduct);
		for (CartDetail cartDetail : cart.getCartDetails()) {
			
			Integer idProductInCartDetail = cartDetail.getProduct().getId();
			if(idProductInCartDetail.equals(idProduct)) {
				Integer productAmountAfter = cartDetail.getAmount()+NUM_CHANGE;
				Double productPrice = cartDetail.getProduct().getPrice();
				if(productAmountAfter>productQuantityInDB) {
					return 0;
				}
				cartDetail.setAmount(productAmountAfter);
				cart.setPrice(cart.getPrice()+productPrice);
				break;
			}
		}
		return 1;
	}

	@Override
	@Transactional
	public Integer downAmountProduct(Integer idProduct) {
		final Integer NUM_CHANGE = 1;
		Integer idCustomer = customerService.getIdCustomerByPrincipal();
		Customer customer = customerService.getCustomerById(idCustomer).isPresent()?customerService.getCustomerById(idCustomer).get():null;
		Cart cart = customer.getCart();
		if(customer == null || cart.getCartDetails().isEmpty()==true) {
			return 0;
		}
		for (CartDetail cartDetail : cart.getCartDetails()) {
			Integer idProductInCartDetail = cartDetail.getProduct().getId();
			if(idProductInCartDetail.equals(idProduct)) {
				Integer productAmountAfter = cartDetail.getAmount()-NUM_CHANGE;
				Double productPrice = cartDetail.getProduct().getPrice();
				if(productAmountAfter<0) {
					return 0;
				}
				if(productAmountAfter==0) {
					cart.getCartDetails().remove(cartDetail);
					return 1;
				}
				cartDetail.setAmount(productAmountAfter);
				cart.setPrice(cart.getPrice()-productPrice);
				break;
			}
		}
		return 1;
	}

	@Override
	@Transactional
	public Integer deleteCartDetailByid(Integer idProduct) {
		Double totalPrice = 0D;
		Integer idCustomer = customerService.getIdCustomerByPrincipal();
		Customer customer = customerService.getCustomerById(idCustomer).isPresent()?customerService.getCustomerById(idCustomer).get():null;
		Cart cart = customer.getCart();
		if(customer == null || cart.getCartDetails().isEmpty()==true) {
			return 0;
		}
		for (CartDetail cartDetail:cart.getCartDetails()) {
			Integer idProductInCartDetail = cartDetail.getProduct().getId();
			if(idProductInCartDetail.equals(idProduct)) {
				cart.getCartDetails().remove(cartDetail);
				break;
			}
		}
		for (CartDetail cartDetailInDB : cart.getCartDetails()) {
			totalPrice += cartDetailInDB.getSubtotal();
		}
		cart.setPrice(totalPrice);
		return 1;
	}

	@Override
	@Transactional
	public Integer deleteAllCartDetail() {
		Integer result = 1;
		Integer idCustomer = customerService.getIdCustomerByPrincipal();
		if (idCustomer == null) {
			result = 0;
		} else {
			Optional<Customer> customer = null;
			Cart cart = null;
			try {
				customer = customerRepository.findById(idCustomer);
				if (customer.isPresent() == false) {
					result = 0;
					customer.orElseThrow(() -> new NotFoundException("Cant find customer with id :" + idCustomer));
				}
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				result = 0;
				e.printStackTrace();
			}
			cart = customer.get().getCart();
			cart.getCartDetails().clear();
			cart.setPrice(0D);
		}
		return result;
	}

	@Override
	@Transactional
	public List<CartDetail> listCartDetail() {
		// TODO Auto-generated method stub
		Integer idCustomer = customerService.getIdCustomerByPrincipal();
		Optional<Customer> customer = null;
		Cart cart = null;
		if (idCustomer == null) {
			return null;
		}
		try {
			customer = customerRepository.findById(idCustomer);
			if (customer.isPresent() == false) {
				customer.orElseThrow(() -> new NotFoundException("Cant find customer with id :" + idCustomer));
			} else {
				cart = customer.get().getCart();
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		List<CartDetail> ls = cart.getCartDetails().stream().collect(Collectors.toList());
		Collections.sort(ls, (o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt()));
		return ls;
	}

	@Override
	@Transactional
	public Long getTotalAmount() {
		// TODO Auto-generated method stub
		String sql = "SELECT sum(c.amount) FROM CartDetail c";
		Query query = this.getSession().createQuery(sql);
		return (Long) query.getSingleResult();
	}

	@Override
	public Double getTotalPrice(List<CartDetail> list) {
		// TODO Auto-generated method stub
		Double total = 0.0;
		for (CartDetail cartDetail : list) {
			total += cartDetail.getSubtotal();
		}
		return total;
	}

}
