package com.seuit.spring.watchshop.service;

import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Feedback;
import com.seuit.spring.watchshop.repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public void saveFeedback(Feedback feedback) {
		feedback.setStatus(0);
		feedbackRepository.save(feedback);
	}

	@Override
	public List<Feedback> getAllFeedback() {
		List<Feedback> listFeedback = feedbackRepository.findAll();
		listFeedback.sort(Comparator.comparing(Feedback::getStatus));
		return listFeedback;
	}

	@Transactional
	@Override
	public void check(Integer id) {
		Session session = this.getSession();
		Feedback feedback = session.get(Feedback.class, id);
		feedback.setStatus(1);
		session.merge(feedback);
	}

}
