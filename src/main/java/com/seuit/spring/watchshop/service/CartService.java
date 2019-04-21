package com.seuit.spring.watchshop.service;

import java.util.Set;

import com.seuit.spring.watchshop.entity.CartAPI;
import com.seuit.spring.watchshop.entity.CartDetail;

public interface CartService {
	public boolean addProductToCart(CartAPI cartAPI);
	public boolean upAmountProduct(Integer idProduct);
	public boolean downAmountProduct(Integer idProduct);
	public boolean deleteCartDetailByid(Integer idProduct);
	public boolean deleteAllCartDetail();
	public Set<CartDetail> listCartDetail();
}
