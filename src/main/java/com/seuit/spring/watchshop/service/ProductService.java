package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.seuit.spring.watchshop.entity.Product;
import com.seuit.spring.watchshop.entity.ProductApi;
import com.seuit.spring.watchshop.entity.ProductDetail;

import javassist.NotFoundException;

public interface ProductService {
	void deleteProductById(Integer id);
	void saveOrUpdate(ProductApi productApi,Integer id);
	List<Product> listProduct( );
	List<ProductDetail> listProductDetail();
	Product getProductById(Integer id) throws NotFoundException;
	ProductDetail getProductDetailByProductId(Integer id) throws NotFoundException;
	List<Product> listProductByIdFirm(Integer id) throws NotFoundException;
	List<Product> listProductByIdModel(Integer id);
	List<Product> listProductByIdOrigin(Integer id);
	Map<String, Object> findPaginated(Integer page,Integer size,Integer idObject,String styleObject,String sort);
	Long countProduct();
	Map<String, Object> getListProductBykeyword(Integer page,Integer size,String keyword,String sort);
	List<Product> listProductTopOrder();
	Map<String, Object> findPaginatedSort(Integer page, Integer size, Integer idFirm, Integer type);
	Integer updownQuantityProduct(Integer id,Integer quantity); 
	Integer getAvailableProduct(Integer id);
}
