package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.Map;

import com.seuit.spring.watchshop.entity.Alert;

public interface AlertService {
	 void create(Alert alert);
	 Map<String, Object> getListAlert(); 
	 Map<String, Object> getListAlertNew(); 
	 void readNewAlert();
	 void deleteById(Integer id);
	 void deleteAll();
}
