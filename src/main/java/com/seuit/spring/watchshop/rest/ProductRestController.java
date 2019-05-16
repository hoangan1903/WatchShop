package com.seuit.spring.watchshop.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.seuit.spring.watchshop.entity.Model;
import com.seuit.spring.watchshop.entity.Product;
import com.seuit.spring.watchshop.entity.ProductApi;
import com.seuit.spring.watchshop.entity.ProductDetail;
import com.seuit.spring.watchshop.service.ProductService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = { "/rest" })
public class ProductRestController {

	public static final Integer maxSizeResultListForIndex = 10;

	@Autowired
	private ProductService productService;

	@PostMapping("/products")
	String newProduct(@Valid @RequestBody ProductApi productApi) {
		productService.saveOrUpdate(productApi, null);
		return "Add success";
	}

	@DeleteMapping("/products/{id}")
	String deleteProduct(@PathVariable(value = "id") @Min(1) Integer id) {
		productService.deleteProductById(id);
		return "Delete Success";
	}

	@PutMapping("/products/{id}")
	String updateProduct(@Valid @RequestBody ProductApi productApi, BindingResult result,
			@PathVariable(value = "id") @Min(1) Integer id) {
		productService.saveOrUpdate(productApi, id);
		return "Update Success";
	}

	@GetMapping(value = "/products",params = {"page","size"})
	Map<String, Object> findAllProductByPaginated(@RequestParam("page") Integer page, @RequestParam("size") Integer size,@RequestParam(name = "firm",required = false) Integer idFirm) {
		return productService.findPaginated(page, size, idFirm);
	}

	@GetMapping("/products/count")
	Long getCountTotalProduct() {
		return productService.countProduct();
	}

	@GetMapping(value = "/products")
	List<Product> findAllProduct() {
		return productService.listProduct();
	}

	@GetMapping("/products/{id}")
	Product findProductByProductId(@PathVariable(value = "id") @Min(1) Integer id) throws NotFoundException {
		return productService.getProductById(id);
	}

	@GetMapping("/products/details")
	List<ProductDetail> findAllProductDetail() {
		return productService.listProductDetail();
	}

	@GetMapping("/products/details/{id}")
	ProductDetail findAllProductDetailByProductId(@PathVariable(value = "id") @Min(1) Integer id)
			throws NotFoundException {
		return productService.getProductDetailByProductId(id);
	}

	@GetMapping("/products/firm/{id}")
	List<Product> findAllProductByIdFirm(@PathVariable(value = "id") Integer id) throws NotFoundException {
		return productService.listProductByIdFirm(id);
	}

	@GetMapping("/products/model/{id}")
	List<Product> findAllProductByIdModel(@PathVariable(value = "id") Integer id) {
		return productService.listProductByIdModel(id);
	}

	@GetMapping("/products/origin/{id}")
	List<Product> findAllProductByIdOrigin(@PathVariable(value = "id") Integer id) {
		return productService.listProductByIdOrigin(id);
	}

	@GetMapping("/products/find")
	Map<String,Object> findProductByKeyword(@RequestParam(name="keyword") String keyword,@RequestParam(name="page",defaultValue = "0",required = false) Integer page,@RequestParam(name="size",defaultValue = "12",required = false) Integer size) {
		return productService.getListProductBykeyword(page,size,keyword);
	}

	@GetMapping("/products/catalogue")
	Map<String, Object> catalogueProductIndex() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Product> listTop = productService.listProductTopOrder();
		List<Product> listCITIZEN = new ArrayList<Product>();
		List<Product> listOGIVAL = new ArrayList<Product>();
		List<Product> listORIENT = new ArrayList<Product>();
		List<Product> listBULOVA = new ArrayList<Product>();
		/*
		 * '1','Citizen' 
		 * '2','Ogival'
		 * '3', 'Elixa' 
		 * '4', 'Bulova' 
		 * '5', 'OP' 
		 * '6','Orient' 
		 * '7', 'Seiko'
		 */
		try {
			listCITIZEN = productService.listProductByIdFirm(1);
			listOGIVAL = productService.listProductByIdFirm(2);
			listORIENT = productService.listProductByIdFirm(6);
			listBULOVA = productService.listProductByIdFirm(4);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("top", createMapProduct(listTop));
		map.put("citizens", createMapProduct(listCITIZEN));
		map.put("ogivals", createMapProduct(listOGIVAL));
		map.put("orients", createMapProduct(listORIENT));
		map.put("bulovas", createMapProduct(listBULOVA));
		return map;
	}

	private Map<String, Object> createMapProduct(List<Product> list) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		Integer size = list.size();
		if (size > maxSizeResultListForIndex) {
			list = list.subList(0, maxSizeResultListForIndex);
		}
		mapList.put("products", list);
		mapList.put("total", list.size());
		return mapList;
	}

}
