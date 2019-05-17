package com.seuit.spring.watchshop.service;

import java.util.List;

import com.seuit.spring.watchshop.entity.Report;

public interface ReportService {
	List<Object> showReport(String from, String till);
	
}
