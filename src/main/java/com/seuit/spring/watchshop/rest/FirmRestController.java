package com.seuit.spring.watchshop.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.entity.Banner;
import com.seuit.spring.watchshop.entity.Firm;
import com.seuit.spring.watchshop.helper.DAHelper;
import com.seuit.spring.watchshop.service.FirmService;

@RestController
@RequestMapping(value = "/rest")
public class FirmRestController {
	
	@Autowired 
	private FirmService firmService;
	
	@GetMapping("/firms")
	public Map<String,Object> getAllFirm(){
		return DAHelper.getInstance().processSubMapWithTotal("firms", firmService.getListFirm());
	}
	
	@PostMapping(value="/firms")
	private void createFirm(@Valid @RequestBody Firm firm) {
		firmService.createFirm(firm);
	}
	
	@DeleteMapping(value="/firms/{id}")
	private void deleteFirmById(@PathVariable(name = "id",required = true) Integer id) {
		firmService.deleteFirmById(id);
	}
	
	@DeleteMapping(value="/firms")
	private void deleteAllFirm() {
		firmService.deleteAllFirm();
	}
	
	@GetMapping("/firms/find/{keyword}")
	private List<Firm> findByKeyword(@PathVariable(name="keyword") String keyword) {
		return firmService.getListBykeyword(keyword);
	}
	
	@GetMapping("/firms/{id}")
	private Object findById(@PathVariable(name="id",required = true) Integer id) {
		return firmService.getByID(id);
	}
	
}
