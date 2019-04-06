package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Firm;
import com.seuit.spring.watchshop.entity.Model;
import com.seuit.spring.watchshop.entity.Origin;
import com.seuit.spring.watchshop.entity.Product;
import com.seuit.spring.watchshop.entity.ProductApi;
import com.seuit.spring.watchshop.entity.ProductDetail;
import com.seuit.spring.watchshop.repository.FirmRepository;
import com.seuit.spring.watchshop.repository.ModelRepository;
import com.seuit.spring.watchshop.repository.OriginRepository;
import com.seuit.spring.watchshop.repository.ProductDetailRepository;
import com.seuit.spring.watchshop.repository.ProductRepository;

import javassist.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductDetailRepository productDetailRepository;

	@Autowired
	private ModelRepository modelRepository;

	@Autowired
	private OriginRepository originRepository;

	@Autowired
	private FirmRepository firmRepository;

	@Override
	@Transactional
	public void deleteProductById(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(ProductApi productApi, Integer id) {
		Product product = productApi.getProduct();
		Optional<Firm> firm = firmRepository.findById(productApi.getIdFirm());
		Optional<Model> model = modelRepository.findById(productApi.getIdModel());
		Optional<Origin> origin = originRepository.findById(productApi.getIdOrigin());
		try {
			firm.orElseThrow(() -> new NotFoundException("id firm khong hop le"));
			origin.orElseThrow(() -> new NotFoundException("id origin khong hop le"));
			model.orElseThrow(() -> new NotFoundException("id model khong hop le"));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Add Product without ID
		if (id == null) {
			ProductDetail productDetail = productApi.getProductDetail();
			productDetail.setModel(model.get());
			productDetail.setOrigin(origin.get());
			productDetail.setProduct(product);
			product.setFirm(firm.get());
			product.setProductDetail(productDetail);
			productRepository.save(product);
		} else {
			//Update Product With ID 
			productRepository.findById(id).map(x -> {
				ProductDetail productDetail = x.getProductDetail();
				productDetail.setModel(model.get());
				productDetail.setOrigin(origin.get());
				productDetail.setSize(productApi.getProductDetail().getSize());
				productDetail.setCaseMaterial(productApi.getProductDetail().getCaseMaterial());
				productDetail.setChainMaterial(productApi.getProductDetail().getChainMaterial());
				productDetail.setGlassMaterial(productApi.getProductDetail().getGlassMaterial());
				productDetail.setInsurance(productApi.getProductDetail().getInsurance());
				productDetail.setOtherFunction(productApi.getProductDetail().getOtherFunction());
				productDetail.setWaterResistance(productApi.getProductDetail().getWaterResistance());
				
				x.setCodeName(product.getCodeName());
				x.setImage(product.getImage());
				x.setPrice(product.getPrice());
				x.setAvailable(product.getAvailable());
				x.setFirm(firm.get());
				x.setProductDetail(productDetail);
				return productRepository.save(x);
			}).orElseGet(()->{
				//Add Product With ID 
				ProductDetail productDetail = productApi.getProductDetail();
				productDetail.setModel(model.get());
				productDetail.setOrigin(origin.get());
				productDetail.setProduct(product);
				logger.info(productDetail.getInsurance().toString());
				product.setFirm(firm.get());
				product.setProductDetail(productDetail);
				return productRepository.save(product);
			});
		}
	}

	@Override
	public List<Product> listProduct() {
		// TODO Auto-generated method stub
		return  productRepository.findAll();
	}

	@Override
	public List<ProductDetail> listProductDetail() {
		// TODO Auto-generated method stub
		return  productDetailRepository.findAll();
	}

	@Override
	public Product productById(Integer id) throws NotFoundException {
		// TODO Auto-generated method stub
		return productRepository.findById(id).map((x)->{return x;}).orElseThrow(()->new NotFoundException("Cant find product with id : "+id));
	}

	

}
