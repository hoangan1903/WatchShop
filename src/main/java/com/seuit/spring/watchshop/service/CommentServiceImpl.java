package com.seuit.spring.watchshop.service;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Comment;
import com.seuit.spring.watchshop.entity.CommentAPI;
import com.seuit.spring.watchshop.entity.Customer;
import com.seuit.spring.watchshop.entity.Product;
import com.seuit.spring.watchshop.entity.ProductDetail;
import com.seuit.spring.watchshop.repository.CommentRepository;
import com.seuit.spring.watchshop.repository.CustomerRepository;
import com.seuit.spring.watchshop.repository.ProductDetailRepository;
import com.seuit.spring.watchshop.repository.ProductRepository;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired 
	private ProductRepository productRepository;
	
	@Autowired
	private CustomerService customerService;

	@Override
	public List<Comment> getComments() {
		// TODO Auto-generated method stub
		return commentRepository.findAll();
	}

	@Override
	public Integer createComment(CommentAPI cmt) {
		// TODO Auto-generated method stub
		 Comment comment = new Comment();
		 Integer idCustomer = customerService.getIdCustomerByPrincipal();
		 Customer customer = customerRepository.findById(idCustomer).isPresent()?customerRepository.findById(idCustomer).get():null;
		 Product product = productRepository.findById(cmt.getIdProduct()).isPresent()?productRepository.findById(cmt.getIdProduct()).get():null;
		 if(customer==null||product==null) {
			 return 0;
		 }
		 ProductDetail productDetail = product.getProductDetail();
		 comment.setContent(cmt.getContent());
		 comment.setCustomer(customer);
		 comment.setProductDetail(productDetail);
		 commentRepository.save(comment);
		 return 1;
	}

	@Override
	public List<Comment> getCommentsWithIdProductDetails(Integer id) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(id).isPresent()?productRepository.findById(id).get():null;
		 if(product==null) {
			 return Collections.emptyList() ;
		 }
		return product.getProductDetail().getComments();
	}
	
	
	
	
}
