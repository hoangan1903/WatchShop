package com.seuit.spring.watchshop.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.entity.Model;
import com.seuit.spring.watchshop.repository.ModelRepository;

@RestController
@RequestMapping(value = "/rest")
public class ModelRestController {
	
	@Autowired
	private ModelRepository modelRepository;
	
	@GetMapping("/model")
	public List<Model> getAllModel(){
		return modelRepository.findAll();
	}
}
