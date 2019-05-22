package com.seuit.spring.watchshop.service;

import java.util.List;

import com.seuit.spring.watchshop.entity.Feedback;

public interface FeedbackService {
	void saveFeedback(Feedback feedback);

	List<Feedback> getAllFeedback();

	void check(Integer id);
}
