package com.seuit.spring.watchshop.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.entity.Comment;
import com.seuit.spring.watchshop.entity.CommentAPI;
import com.seuit.spring.watchshop.service.CommentService;

@RestController
@RequestMapping(value = "/rest/comments")
public class CommentRestController {
	@Autowired 
	private CommentService commentService;
	
	@GetMapping("")
	private List<Comment> listComment(){
		return commentService.getComments();
	}
	
	@PostMapping("")
	private Integer createComment(@Valid @RequestBody CommentAPI cmt) {
		return commentService.createComment(cmt);	
	}
	@GetMapping("/product/{id}")
	private List<Comment> listCommentWithIdProductDetail(@PathVariable(name = "id") Integer id){
		return commentService.getCommentsWithIdProductDetails(id);
	}
}
