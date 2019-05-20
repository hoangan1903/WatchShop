package com.seuit.spring.watchshop.service;

import java.util.Set;

import com.seuit.spring.watchshop.entity.CartAPI;
import com.seuit.spring.watchshop.entity.CartDetail;

public interface CartService {
	public Integer addProductToCart(CartAPI cartAPI);
	public Integer upAmountProduct(Integer idProduct);
	public Integer downAmountProduct(Integer idProduct);
	public Integer deleteCartDetailByid(Integer idProduct);
	public Integer deleteAllCartDetail();
	public Set<CartDetail> listCartDetail();
	public Long getTotalAmount();
}
