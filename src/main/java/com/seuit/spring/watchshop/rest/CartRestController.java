package com.seuit.spring.watchshop.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.entity.CartAPI;
import com.seuit.spring.watchshop.entity.CartDetail;
import com.seuit.spring.watchshop.service.CartService;
import com.seuit.spring.watchshop.service.CustomerService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value="/rest")
public class CartRestController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/cart")
	private Map<String,Object> listCartDetail(){
		Map<String, Object> map = new HashMap<>();
		map.put("totalAmount", cartService.getTotalAmount());
		map.put("totals", cartService.getTotalPrice(cartService.listCartDetail()));
		map.put("cart", cartService.listCartDetail());
		return map;
	}
	
	@PostMapping("/cart")
	private Integer addProductToCart(@Valid @RequestBody CartAPI cartAPI) throws NotFoundException {
		return cartService.addProductToCart(cartAPI);
	}
	@PutMapping("/cart/up")
	private Integer upAmountProduct(@Valid @RequestBody CartAPI cartAPI) {
		return cartService.upAmountProduct(cartAPI.getIdProduct());
	}
	@PutMapping("/cart/down")
	private Integer downAmountProduct(@Valid @RequestBody CartAPI cartAPI) {
		return cartService.downAmountProduct(cartAPI.getIdProduct());
		
	}

	@DeleteMapping("/cart/product/{id}")
	private Integer deleteProductById(@PathVariable(name="id") Integer id) {
		return cartService.deleteCartDetailByid(id);
	}

	@DeleteMapping("/cart/all")
	private Integer deleteAllProductInCart() {
		return cartService.deleteAllCartDetail();
	}
}
