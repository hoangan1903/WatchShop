package com.seuit.spring.watchshop.service;

import java.util.HashSet;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Cart;
import com.seuit.spring.watchshop.entity.CartAPI;
import com.seuit.spring.watchshop.entity.CartDetail;
import com.seuit.spring.watchshop.entity.Customer;
import com.seuit.spring.watchshop.entity.Product;
import com.seuit.spring.watchshop.repository.CartRepository;
import com.seuit.spring.watchshop.repository.CustomerRepository;
import com.seuit.spring.watchshop.repository.ProductRepository;

import javassist.NotFoundException;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	@Transactional
	public boolean addProductToCart(CartAPI cartAPI) {
		// TODO Auto-generated method stub
		Integer idCustomer = cartAPI.getIdCustomer();
		Integer idProduct = cartAPI.getIdProduct();
		Integer amount = cartAPI.getAmount();
		boolean result = true;
		Optional<Product> product = null;
		Optional<Customer> customer = null;
		Cart cart = null;
		try {
			product = productRepository.findById(idProduct);
			customer = customerRepository.findById(idCustomer);
			if(product.isPresent()==false || customer.isPresent()==false) {
				result = false;
				product.orElseThrow(()->new NotFoundException("Cant find product with id :"+idProduct));
				customer.orElseThrow(()->new NotFoundException("Cant find customer with id :"+idCustomer));
			}else {
				cart = customer.get().getCart();
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CartDetail cartDetail = new CartDetail(cart,product.get(),amount);
		Optional<CartDetail> isDuplicateCartDetail =cart.getCartDetails().stream().filter((c)-> c.getProduct().getId()==cartDetail.getProduct().getId()&&c.getCart().getId()==cartDetail.getCart().getId()).findFirst();
		//isDuplicateCartDetail == false : add new ok 
		if(isDuplicateCartDetail.isPresent()==false) {
			cart.getCartDetails().add(cartDetail);
		}else {
			isDuplicateCartDetail.get().setAmount(isDuplicateCartDetail.get().getAmount()+amount);
		}
		return result;
	}

	@Override
	@Transactional
	public boolean upAmountProduct(Integer idCustomer,Integer idProduct) {
		// TODO Auto-generated method stub
				boolean result = true;
				Optional<Product> product = null;
				Optional<Customer> customer = null;
				Cart cart = null;
				try {
					product = productRepository.findById(idProduct);
					customer = customerRepository.findById(idCustomer);
					if(product.isPresent()==false || customer.isPresent()==false) {
						result = false;
						product.orElseThrow(()->new NotFoundException("Cant find product with id :"+idProduct));
						customer.orElseThrow(()->new NotFoundException("Cant find customer with id :"+idCustomer));
					}else {
						cart = customer.get().getCart();
					}
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CartDetail cartDetail = new CartDetail(cart,product.get(),null);
				Optional<CartDetail> isDuplicateCartDetail =cart.getCartDetails().stream().filter((c)-> c.getProduct().getId()==cartDetail.getProduct().getId()&&c.getCart().getId()==cartDetail.getCart().getId()).findFirst();
				if(isDuplicateCartDetail.isPresent()==true) {
					isDuplicateCartDetail.get().setAmount(isDuplicateCartDetail.get().getAmount()+1);
				}
				return result;
	}

	@Override
	@Transactional
	public boolean downAmountProduct(Integer idCustomer,Integer idProduct) {
		boolean result = true;
		Optional<Product> product = null;
		Optional<Customer> customer = null;
		Cart cart = null;
		try {
			product = productRepository.findById(idProduct);
			customer = customerRepository.findById(idCustomer);
			if(product.isPresent()==false || customer.isPresent()==false) {
				result = false;
				product.orElseThrow(()->new NotFoundException("Cant find product with id :"+idProduct));
				customer.orElseThrow(()->new NotFoundException("Cant find customer with id :"+idCustomer));
			}else {
				cart = customer.get().getCart();
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CartDetail cartDetail = new CartDetail(cart,product.get(),null);
		Optional<CartDetail> isDuplicateCartDetail =cart.getCartDetails().stream().filter((c)-> c.getProduct().getId()==cartDetail.getProduct().getId()&&c.getCart().getId()==cartDetail.getCart().getId()).findFirst();
		if(isDuplicateCartDetail.isPresent()==true) {
			isDuplicateCartDetail.get().setAmount(isDuplicateCartDetail.get().getAmount()-1);
		}
		return result;
	}

	@Override
	@Transactional
	public boolean deleteCartDetailByid(Integer idCustomer, Integer idProduct) {
		boolean result = true;
		Optional<Product> product = null;
		Optional<Customer> customer = null;
		Cart cart = null;
		try {
			product = productRepository.findById(idProduct);
			customer = customerRepository.findById(idCustomer);
			if(product.isPresent()==false || customer.isPresent()==false) {
				result = false;
				product.orElseThrow(()->new NotFoundException("Cant find product with id :"+idProduct));
				customer.orElseThrow(()->new NotFoundException("Cant find customer with id :"+idCustomer));
			}else {
				cart = customer.get().getCart();
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CartDetail cartDetail = new CartDetail(cart,product.get(),null);
		Optional<CartDetail> isDuplicateCartDetail =cart.getCartDetails().stream().filter((c)-> c.getProduct().getId()==cartDetail.getProduct().getId()&&c.getCart().getId()==cartDetail.getCart().getId()).findFirst();
		if(isDuplicateCartDetail.isPresent()==true) {
			cart.getCartDetails().remove(isDuplicateCartDetail.get());
		}
		return result;
	}

	@Override
	@Transactional
	public boolean deleteAllCartDetail(Integer idCustomer) {
		boolean result = true;
		Optional<Customer> customer = null;
		Cart cart = null;
		try {
			customer = customerRepository.findById(idCustomer);
			if(customer.isPresent()==false) {
				result = false;
				customer.orElseThrow(()->new NotFoundException("Cant find customer with id :"+idCustomer));
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			result = false;
			e.printStackTrace();
		}
		cart = customer.get().getCart();
		cart.getCartDetails().clear();
		return result;
	}
}
