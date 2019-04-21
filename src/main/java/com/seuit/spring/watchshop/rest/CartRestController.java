package com.seuit.spring.watchshop.rest;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

@RestController
@RequestMapping(value="/rest")
public class CartRestController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/cart")
	private Set<CartDetail> listCartDetail(){
		return cartService.listCartDetail();
	}
	
	@PostMapping("/cart")
	private String addProductToCart(@Valid @RequestBody CartAPI cartAPI) {
		boolean boo = cartService.addProductToCart(cartAPI);
		if(boo==true) {
			return "add product to cart success";
		}
		return "add product to cart fail";
	}
	@PutMapping("/cart/up")
	private String upAmountProduct(@Valid @RequestBody CartAPI cartAPI) {
		boolean boo = cartService.upAmountProduct(cartAPI.getIdProduct());
		if(boo==true) {
			return "up amount product to success";
		}
		return "up amount product to fail";
	}
	@PutMapping("/cart/down")
	private String downAmountProduct(@Valid @RequestBody CartAPI cartAPI) {
		boolean boo = cartService.downAmountProduct(cartAPI.getIdProduct());
		if(boo==true) {
			return "down amount product to success";
		}
		return "down amount product to fail";
	}
	//Have bug
	@DeleteMapping("/cart/product")
	private String deleteC(@Valid @RequestBody CartAPI cartAPI) {
		boolean boo = cartService.deleteCartDetailByid(cartAPI.getIdProduct());
		if(boo==true) {
			return "delete product to success";
		}
		return "delete product to fail";
	}

	@DeleteMapping("/cart/all")
	private String deleteAllProductInCart() {
		boolean boo = cartService.deleteAllCartDetail();
		if(boo==true) {
			return "delete all product to success";
		}
		return "delete all amount product to fail";
	}
}
