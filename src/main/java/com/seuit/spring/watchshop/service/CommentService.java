package com.seuit.spring.watchshop.service;

import java.util.List;

import com.seuit.spring.watchshop.entity.Comment;
import com.seuit.spring.watchshop.entity.CommentAPI;

public interface CommentService {
	List<Comment> getComments();
	void createComment(CommentAPI cmt);
	List<Comment> getCommentsWithIdProductDetails(Integer id);
}
