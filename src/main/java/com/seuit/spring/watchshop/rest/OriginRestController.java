package com.seuit.spring.watchshop.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.entity.Origin;
import com.seuit.spring.watchshop.repository.OriginRepository;

@RestController
@RequestMapping(value = "/rest")
public class OriginRestController {
	@Autowired
	private OriginRepository originRepository;
	
	@GetMapping("/origin")
	public List<Origin> getAllOrigin(){
		return originRepository.findAll();
	}
}
