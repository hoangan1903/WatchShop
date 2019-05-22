package com.seuit.spring.watchshop.rest;

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

import com.seuit.spring.watchshop.entity.Alert;
import com.seuit.spring.watchshop.service.AlertService;

@RestController
@RequestMapping(value="/rest/alerts")
public class AlertRestController {
	
	@Autowired
	private AlertService alertService;
	
	@GetMapping("")
	private Map<String,Object> getAll(){
		return alertService.getListAlert();
	}
	
	@GetMapping("/new")
	private Map<String,Object> getAllNew(){
		return alertService.getListAlertNew();
	}
	
	@PostMapping("")
	private void create(@Valid @RequestBody Alert alert) {
		alertService.create(alert);
	}
	
	@PostMapping("/read")
	private void read() {
		alertService.readNewAlert();
	}
	
	@DeleteMapping("/{id}")
	private void deleteById(@PathVariable(name = "id") Integer id) {
		alertService.deleteById(id);
	}
	
	@DeleteMapping("")
	private void deleteAll() {
		alertService.deleteAll();
	}
}
