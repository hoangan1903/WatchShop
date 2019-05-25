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
		Optional<Product> product = productRepository.findById(idProduct);
		Optional<Customer> customer = customerRepository.findById(idCustomer);

		// Kiểm tra xem số lượng sản phẩm trên giỏ hàng có lớn hơn số lượng sản phẩm còn
		// trong kho không ?
		// Kiểm tra xem khách hàng có tồn tại không ?
		//Kiểm tra xem 
		if (amount > productService.getAvailableProduct(idProduct) || idCustomer == null || product.isPresent() == false) {
			product.orElseThrow(() -> new NotFoundException("Cant find product with id :" + idProduct));
			customer.orElseThrow(() -> new NotFoundException("Cant find customer with id :" + idCustomer));
			return 0;
		}
		Cart cart = customer.get().getCart();
		CartDetail cartDetail = new CartDetail(cart, product.get(), amount);
		Optional<CartDetail> isDuplicateCartDetail = cart.getCartDetails().stream()
				.filter((c) -> c.getProduct().getId() == cartDetail.getProduct().getId()
						&& c.getCart().getId() == cartDetail.getCart().getId())
				.findFirst();
		// isDuplicateCartDetail == false : add new ok
		if (isDuplicateCartDetail.isPresent() == false) {
			cart.getCartDetails().add(cartDetail);
		} else {
			isDuplicateCartDetail.get().setAmount(isDuplicateCartDetail.get().getAmount() + amount);
		}

		return 1;
	}

	@Override
	@Transactional
	public Integer upAmountProduct(Integer idProduct) {
		Integer result = 1;
		Integer idCustomer = customerService.getIdCustomerByPrincipal();
		if (idCustomer == null) {
			result = 0;
		} else {
			Optional<Product> product = null;
			Optional<Customer> customer = null;
			Cart cart = null;
			try {
				product = productRepository.findById(idProduct);
				customer = customerRepository.findById(idCustomer);
				if (product.isPresent() == false || customer.isPresent() == false) {
					result = 0;
					product.orElseThrow(() -> new NotFoundException("Cant find product with id :" + idProduct));
					customer.orElseThrow(() -> new NotFoundException("Cant find customer with id :" + idCustomer));
				} else {
					cart = customer.get().getCart();
				}
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				result = 0;
				e.printStackTrace();
			}
			CartDetail cartDetail = new CartDetail(cart, product.get(), null);
			Optional<CartDetail> isDuplicateCartDetail = cart.getCartDetails().stream()
					.filter((c) -> c.getProduct().getId() == cartDetail.getProduct().getId()
							&& c.getCart().getId() == cartDetail.getCart().getId())
					.findFirst();
			if (isDuplicateCartDetail.isPresent() == true) {
				if (isDuplicateCartDetail.get().getAmount() >= isDuplicateCartDetail.get().getProduct()
						.getAvailable()) {
					return 0;
				}
				isDuplicateCartDetail.get().setAmount(isDuplicateCartDetail.get().getAmount() + 1);
			}
		}
		return result;
	}

	@Override
	@Transactional
	public Integer downAmountProduct(Integer idProduct) {
		Integer result = 1;
		Integer idCustomer = customerService.getIdCustomerByPrincipal();
		if (idCustomer == null) {
			result = 0;
		} else {
			Optional<Product> product = null;
			Optional<Customer> customer = null;
			Cart cart = null;
			try {
				product = productRepository.findById(idProduct);
				customer = customerRepository.findById(idCustomer);
				if (product.isPresent() == false || customer.isPresent() == false) {
					result = 0;
					product.orElseThrow(() -> new NotFoundException("Cant find product with id :" + idProduct));
					customer.orElseThrow(() -> new NotFoundException("Cant find customer with id :" + idCustomer));
				} else {
					cart = customer.get().getCart();
				}
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				result = 0;
				e.printStackTrace();
			}
			CartDetail cartDetail = new CartDetail(cart, product.get(), null);
			Optional<CartDetail> isDuplicateCartDetail = cart.getCartDetails().stream()
					.filter((c) -> c.getProduct().getId() == cartDetail.getProduct().getId()
							&& c.getCart().getId() == cartDetail.getCart().getId())
					.findFirst();
			if (isDuplicateCartDetail.isPresent() == true) {
				if (isDuplicateCartDetail.get().getAmount() <= 0) {
					return 0;
				}
				isDuplicateCartDetail.get().setAmount(isDuplicateCartDetail.get().getAmount() - 1);
			}
		}
		return result;
	}

	@Override
	@Transactional
	public Integer deleteCartDetailByid(Integer idProduct) {
		Integer result = 1;
		Integer idCustomer = customerService.getIdCustomerByPrincipal();
		if (idCustomer == null) {
			result = 0;
		} else {
			Optional<Product> product = null;
			Optional<Customer> customer = null;
			Cart cart = null;
			try {
				product = productRepository.findById(idProduct);
				customer = customerRepository.findById(idCustomer);
				if (product.isPresent() == false || customer.isPresent() == false) {
					result = 0;
					product.orElseThrow(() -> new NotFoundException("Cant find product with id :" + idProduct));
					customer.orElseThrow(() -> new NotFoundException("Cant find customer with id :" + idCustomer));
				} else {
					cart = customer.get().getCart();
				}
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				result = 0;
				e.printStackTrace();
			}
			CartDetail cartDetail = new CartDetail(cart, product.get(), null);
			Optional<CartDetail> isDuplicateCartDetail = cart.getCartDetails().stream()
					.filter((c) -> c.getProduct().getId() == cartDetail.getProduct().getId()
							&& c.getCart().getId() == cartDetail.getCart().getId())
					.findFirst();
			if (isDuplicateCartDetail.isPresent() == true) {
				cart.getCartDetails().remove(isDuplicateCartDetail.get());
			}
		}
		return result;
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
