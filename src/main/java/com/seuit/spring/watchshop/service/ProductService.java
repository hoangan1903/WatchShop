package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Optional;
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
	Product getProductById(Integer id) throws NotFoundException;
	ProductDetail getProductDetailByProductId(Integer id) throws NotFoundException;
	Set<Product> listProductByIdFirm(Integer id) throws NotFoundException;
	List<Product> listProductByIdModel(Integer id);
	List<Product> listProductByIdOrigin(Integer id);
}
