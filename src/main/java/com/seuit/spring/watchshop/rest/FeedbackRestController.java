package com.seuit.spring.watchshop.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.entity.Feedback;
import com.seuit.spring.watchshop.service.FeedbackService;

@RestController
@RequestMapping(value = { "/rest" })
public class FeedbackRestController {
	@Autowired
	FeedbackService feedbackService;

	@PostMapping("/feedback")
	String saveFeedback(@Valid @RequestBody Feedback feedback) {
		feedbackService.saveFeedback(feedback);
		return "Save success";
	}
	
	@GetMapping(value="/feedback")
	List<Feedback> showAllFeedback(){
		return feedbackService.getAllFeedback();
	}
	
	@PostMapping(value="/feedback/check", params= {"id"})
	String checkFeedback(@RequestParam(name = "id") Integer id) {
		feedbackService.check(id);
		return "check Susscess";
	}
}
