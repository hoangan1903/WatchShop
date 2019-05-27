package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Set;

import com.seuit.spring.watchshop.entity.CartAPI;
import com.seuit.spring.watchshop.entity.CartDetail;

import javassist.NotFoundException;

public interface CartService {
	public Integer addProductToCart(CartAPI cartAPI) throws NotFoundException;
	public Integer upAmountProduct(Integer idProduct);
	public Integer downAmountProduct(Integer idProduct);
	public Integer deleteCartDetailByid(Integer idProduct);
	public Integer deleteAllCartDetail();
	public List<CartDetail> listCartDetail();
	public Long getTotalAmount();
	public Double getTotalPrice(List<CartDetail> list);
}
