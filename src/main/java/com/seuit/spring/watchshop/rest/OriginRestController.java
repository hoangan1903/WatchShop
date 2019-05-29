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
import com.seuit.spring.watchshop.entity.Origin;
import com.seuit.spring.watchshop.helper.DAHelper;
import com.seuit.spring.watchshop.service.OriginService;

@RestController
@RequestMapping(value = "/rest")
public class OriginRestController {
	@Autowired
	private OriginService originService;
	
	@GetMapping("/origins")
	public Map<String,Object> getAll(){
		return DAHelper.getInstance().processSubMapWithTotal("origins", originService.getList());
	}
	
	@PostMapping("/origins")
	public void create(@Valid @RequestBody Origin origin) {
		originService.create(origin);
	}
	@DeleteMapping("/origins/{id}")
	public void deleteById(@PathVariable(name="id",required = true) Integer id) {
		originService.deleteById(id);
	}
	
	@DeleteMapping("/origins")
	public void deleteAll() {
		originService.deleteAll();
	}
	@GetMapping("/origins/find/{keyword}")
	private List<Origin> findByKeyword(@PathVariable(name="keyword") String keyword) {
		return originService.getListBykeyword(keyword);
	}
	@GetMapping("/origins/{id}")
	private Object findById(@PathVariable(name="id",required = true) Integer id) {
		return originService.getByID(id);
	}
}
