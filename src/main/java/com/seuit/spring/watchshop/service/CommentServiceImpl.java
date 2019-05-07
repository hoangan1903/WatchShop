package com.seuit.spring.watchshop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Comment;
import com.seuit.spring.watchshop.entity.CommentAPI;
import com.seuit.spring.watchshop.entity.Customer;
import com.seuit.spring.watchshop.entity.ProductDetail;
import com.seuit.spring.watchshop.repository.CommentRepository;
import com.seuit.spring.watchshop.repository.CustomerRepository;
import com.seuit.spring.watchshop.repository.ProductDetailRepository;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired 
	private ProductDetailRepository productDetailRepository;

	@Override
	public List<Comment> getComments() {
		// TODO Auto-generated method stub
		return commentRepository.findAll();
	}

	@Override
	public void createComment(CommentAPI cmt) {
		// TODO Auto-generated method stub
		 Comment comment = new Comment();
		 Customer cus = customerRepository.findById(cmt.getIdCustomer()).get();
		 ProductDetail pro = productDetailRepository.findById(cmt.getIdProductDetail()).get();
		 comment.setContent(cmt.getComment().getContent());
		 comment.setCustomer(cus);
		 comment.setProductDetail(pro);
		 commentRepository.save(comment);
	}

	@Override
	public List<Comment> getCommentsWithIdProductDetails(Integer id) {
		// TODO Auto-generated method stub
		ProductDetail pro = productDetailRepository.findById(id).get();
		return pro.getComments();
	}
	
	
	
	
}
