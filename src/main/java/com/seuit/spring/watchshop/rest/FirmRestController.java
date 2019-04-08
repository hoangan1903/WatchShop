package com.seuit.spring.watchshop.rest;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.entity.Firm;
import com.seuit.spring.watchshop.repository.FirmRepository;

@RestController
@RequestMapping(value = "/rest")
public class FirmRestController {
	
	@Autowired 
	private FirmRepository firmRepository;
	
	@GetMapping("/firm")
	public List<Firm> getAllFirm(){
		return firmRepository.findAll();
	}
}
