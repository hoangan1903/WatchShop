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

import com.seuit.spring.watchshop.entity.Firm;
import com.seuit.spring.watchshop.entity.Model;
import com.seuit.spring.watchshop.helper.DAHelper;
import com.seuit.spring.watchshop.service.ModelService;

@RestController
@RequestMapping(value = "/rest")
public class ModelRestController {
	
	@Autowired
	private ModelService modelService;
	
	@GetMapping("/models")
	public Map<String,Object> getAll(){
		return DAHelper.getInstance().processSubMapWithTotal("models", modelService.getList());
	}
	
	@PostMapping("/models")
	public void create(@Valid @RequestBody Model model) {
		modelService.create(model);
	}
	@DeleteMapping("/models/{id}")
	public void deleteById(@PathVariable(name="id",required = true) Integer id) {
		modelService.deleteById(id);
	}
	
	@DeleteMapping("/models")
	public void deleteAll() {
		modelService.deleteAll();
	}
	@GetMapping("/models/find/{keyword}")
	private List<Model> findByKeyword(@PathVariable(name="keyword") String keyword) {
		return modelService.getListBykeyword(keyword);
	}
	
	@GetMapping("/models/{id}")
	private Object findById(@PathVariable(name="id",required = true) Integer id) {
		return modelService.getByID(id);
	}
}
