package com.seuit.spring.watchshop.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Firm;
import com.seuit.spring.watchshop.entity.Image;
import com.seuit.spring.watchshop.entity.Model;
import com.seuit.spring.watchshop.entity.Origin;
import com.seuit.spring.watchshop.entity.Product;
import com.seuit.spring.watchshop.entity.ProductApi;
import com.seuit.spring.watchshop.entity.ProductDetail;
import com.seuit.spring.watchshop.helper.DAHelper;
import com.seuit.spring.watchshop.repository.FirmRepository;
import com.seuit.spring.watchshop.repository.ModelRepository;
import com.seuit.spring.watchshop.repository.OriginRepository;
import com.seuit.spring.watchshop.repository.ProductDetailRepository;
import com.seuit.spring.watchshop.repository.ProductRepository;

import javassist.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

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
			productRepository.save(ExecProductAPI(productApi, product, firm, model, origin));
		} else {
			// Update Product With ID
			productRepository.findById(id).map(x -> {
				// Get ProductDetail & Images From x
				ProductDetail productDetail = x.getProductDetail();
				Set<Image> images = x.getProductDetail().getImages();

				images = productApi.getImages();
				images.stream().forEach((image) -> {
					image.setProductDetail(productDetail);
				});
				productDetail.setImages(images);
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
			}).orElseGet(() -> {
				// Add Product With ID
				return productRepository.save(ExecProductAPI(productApi, product, firm, model, origin));
			});
		}
	}

	private Product ExecProductAPI(ProductApi productApi, Product product, Optional<Firm> firm, Optional<Model> model,
			Optional<Origin> origin) {
		ProductDetail productDetail = productApi.getProductDetail();
		Set<Image> images = productApi.getImages();
		images.stream().forEach((image) -> {
			image.setProductDetail(productDetail);
		});
		productDetail.setImages(images);
		productDetail.setModel(model.get());
		productDetail.setOrigin(origin.get());
		productDetail.setProduct(product);
		product.setFirm(firm.get());
		product.setProductDetail(productDetail);
		return product;
	}

	@Override
	@Transactional
	public List<Product> listProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	@Transactional
	public List<ProductDetail> listProductDetail() {
		// TODO Auto-generated method stub
		return productDetailRepository.findAll();
	}

	@Override
	@Transactional
	public ProductDetail getProductDetailByProductId(Integer id) throws NotFoundException {
		// TODO Auto-generated method stub
		return productRepository.findById(id).map((x) -> {
			return x.getProductDetail();
		}).orElseThrow(() -> new NotFoundException("Cant find product with id : " + id));
	}

	@Override
	@Transactional
	public Product getProductById(Integer id) throws NotFoundException {
		// TODO Auto-generated method stub
		return productRepository.findById(id).map((x) -> {
			return x;
		}).orElseThrow(() -> new NotFoundException("Cant find product with id : " + id));
	}

	@Override
	@Transactional
	public List<Product> listProductByIdFirm(Integer id) throws NotFoundException {
		// TODO Auto-generated method stub
		return firmRepository.findById(id).map((firm) -> {
			return firm.getProducts();
		}).orElseThrow(() -> new NotFoundException("Find not Id Firm"));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> listProductByIdModel(Integer id) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sql = "Select p FROM Product p inner join ProductDetail pd on p.productDetail=pd.id inner join Model m on pd.model=m.id WHERE m.id=:id";
		Query query = session.createQuery(sql);
		query.setParameter("id", id);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> listProductByIdOrigin(Integer id) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sql = "Select p FROM Product p inner join ProductDetail pd on p.productDetail=pd.id inner join Origin o on pd.origin=o.id WHERE o.id=:id";
		Query query = session.createQuery(sql);
		query.setParameter("id", id);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Map<String, Object> findPaginated(Integer page, Integer size, Integer idObject, String styleObject,
			String sort) {
		System.out.println("start");
		Session session = getSession();
		String sql = null;
		Query query = null;
		Map<String, Object> map = new HashMap<String, Object>();
		Integer pageCount;
		List<Product> listProduct = null;
		String sortClause = " ";
		if(sort!=null) {
			sortClause = "ORDER BY p.price "+sort;
		}
		if (idObject != null) {
			if (styleObject == "Firm") {
				sql = "Select p FROM Product p inner join Firm f on p.firm.id=f.id WHERE f.id=:idObject "+sortClause;
				query = session.createQuery(sql);
				query.setParameter("idObject", idObject);
			} else {
				// model && origin
				if (styleObject == "Model") {
					sql = "Select p FROM Product p inner join ProductDetail pd on p.productDetail.id=pd.id inner join Model f on pd.model.id =f.id WHERE f.id=:idObject ORDER BY p.price "+ sortClause;
				}
				if (styleObject == "Origin") {
					sql = "Select p FROM Product p inner join ProductDetail pd on p.productDetail.id=pd.id inner join Origin f on pd.origin.id =f.id WHERE f.id=:idObject ORDER BY p.price " + sortClause;
				}
				query = session.createQuery(sql);
				query.setParameter("idObject", idObject);
			}
			listProduct = query.getResultList();
			if (listProduct.size() % size == 0) {// chia het cho size
				pageCount = listProduct.size() / size;
			} else {
				pageCount = listProduct.size() / size + 1;
			}
			
			query.setFirstResult(page * size).setMaxResults(size);
		} else {
			sql = "FROM Product "+ sortClause;
			query = session.createQuery(sql);
			listProduct = query.getResultList();
			if (listProduct.size() % size == 0) {// chia het cho size
				pageCount = listProduct.size() / size;
			} else {
				pageCount = listProduct.size() / size + 1;
			}
			query.setFirstResult(page * size).setMaxResults(size);
		}
		listProduct = query.getResultList();
		map.put("pageCountTotal", pageCount);
		map.put("products", listProduct);
		return map;
	}

	@Override
	@Transactional
	public Long countProduct() {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sqlCount = "SELECT count(p.id) FROM Product p";
		Query queryCount = session.createQuery(sqlCount);
		Long count = (Long) queryCount.getSingleResult();
		return count;

	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public Map<String, Object> getListProductBykeyword(Integer page, Integer size, String keyword,String sort) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		Integer pageCount;
		List<Product> listProduct = null;
		String sortClause = " ";
		if(sort!=null) {
			sortClause = "ORDER BY p.price "+sort;
		}
		String sql = "SELECT p FROM Product p WHERE p.codeName like :code "+sortClause;
		Query query = session.createQuery(sql);
		query.setParameter("code", "%" + keyword + "%");
		listProduct = query.getResultList();

		if (page == null && size == null) {
			map.put("products", listProduct);
			return map;
		}

		if (listProduct.size() % size == 0) {// chia het cho size
			pageCount = listProduct.size() / size;
		} else {
			pageCount = listProduct.size() / size + 1;
		}
		query.setFirstResult(page * size).setMaxResults(size);
		listProduct = query.getResultList();
		map.put("pageCountTotal", pageCount);
		map.put("products", listProduct);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> listProductTopOrder() {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		String sql = "SELECT p FROM Product p inner join OrderDetail o " + "on p.id=o.productO.id " + "group by p.id "
				+ "order by count(p.id)desc";
		Query query = session.createQuery(sql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Map<String, Object> findPaginatedSort(Integer page, Integer size, Integer idFirm, Integer type) {
		Session session = getSession();
		String sql = null;
		Query query;
		Map<String, Object> map = new HashMap<String, Object>();
		Integer pageCount;
		List<Product> listProduct = null;
		if (idFirm != null) {
			if (type == 0) {
				sql = "Select p FROM Product p inner join Firm f on p.firm.id=f.id WHERE f.id=:idFirm order by p.price asc";
			} else if (type == 1) {
				sql = "Select p FROM Product p inner join Firm f on p.firm.id=f.id WHERE f.id=:idFirm order by p.price desc";
			}
			query = session.createQuery(sql);
			query.setParameter("idFirm", idFirm);
			listProduct = query.getResultList();
			if (listProduct.size() % size == 0) {// chia het cho size
				pageCount = listProduct.size() / size;
			} else {
				pageCount = listProduct.size() / size + 1;
			}
			query.setFirstResult(page * size).setMaxResults(size);
		} else {
			if (type == 0)
				sql = "FROM Product p order by p.price asc";
			else
				sql = "FROM Product p order by p.price desc";
			query = session.createQuery(sql);
			listProduct = query.getResultList();
			if (listProduct.size() % size == 0) {// chia het cho size
				pageCount = listProduct.size() / size;
			} else {
				pageCount = listProduct.size() / size + 1;
			}
			query.setFirstResult(page * size).setMaxResults(size);
		}
		map.put("pageCountTotal", pageCount);
		map.put("products", query.getResultList());
		return map;
	}

	@Override
	@Transactional
	public Integer updownQuantityProduct(Integer id, Integer quantity) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Integer sum = 0;
		Product product = session.get(Product.class, id);
		if (product.getId() == null)
			return 0;
		if (quantity + this.getAvailableProduct(id) <= 0)
			sum = 0;
		else
			sum = quantity + this.getAvailableProduct(id);
		product.setAvailable(sum);
		session.merge(product);
		return 1;
	}

	@Override
	public synchronized Integer getAvailableProduct(Integer id) {
		// TODO Auto-generated method stub
		Optional<Product> product = productRepository.findById(id);
		try {
			product.orElseThrow(() -> new NotFoundException("cant find"));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product.get().getAvailable();
	}

}
