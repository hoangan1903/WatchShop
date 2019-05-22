package com.seuit.spring.watchshop.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.entity.Report;
import com.seuit.spring.watchshop.service.ReportService;

@RestController
@RequestMapping(value= {"/rest"})
public class ReportRestController {

	@Autowired
	ReportService reportService;
	
	@GetMapping(value = "/report", params = {"from","till"})
	List<Object> showReport(@RequestParam("from") String from, @RequestParam("till") String till){
		return reportService.showReport(from, till);
	}
}
