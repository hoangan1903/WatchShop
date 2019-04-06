package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Set;

import com.seuit.spring.watchshop.entity.Product;
import com.seuit.spring.watchshop.entity.ProductApi;
import com.seuit.spring.watchshop.entity.ProductDetail;

import javassist.NotFoundException;

public interface ProductService {
	void deleteProductById(Integer id);
	void saveOrUpdate(ProductApi productApi,Integer id);
	List<Product> listProduct();
	List<ProductDetail> listProductDetail();
	Product productById(Integer id) throws NotFoundException;
}
