package com.seuit.spring.watchshop.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
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
	private ProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@Override
	@Transactional
	public Integer addProductToCart(CartAPI cartAPI) {
		// TODO Auto-generated method stub
		Integer result = 1;
		Integer idCustomer = customerService.getIdCustomerByPrincipal();
		if (idCustomer == null) {
			result = 0;
		} else {
			System.out.println(idCustomer);
			Integer idProduct = cartAPI.getIdProduct();
			Integer amount = cartAPI.getAmount();
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
				e.printStackTrace();
			}
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
		}
		return result;
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
	public Set<CartDetail> listCartDetail() {
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
			}else {
				cart = customer.get().getCart();
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return cart.getCartDetails().stream().collect(Collectors.toSet());
	}
}
