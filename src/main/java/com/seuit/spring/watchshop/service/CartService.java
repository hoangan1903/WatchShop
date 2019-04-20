package com.seuit.spring.watchshop.service;

import com.seuit.spring.watchshop.entity.CartAPI;

public interface CartService {
	public boolean addProductToCart(CartAPI cartAPI);
	public boolean upAmountProduct(Integer idCustomer,Integer idProduct);
	public boolean downAmountProduct(Integer idCustomer,Integer idProduct);
	public boolean deleteCartDetailByid(Integer idCustomer,Integer idProduct);
	public boolean deleteAllCartDetail(Integer idCustomer);
}
