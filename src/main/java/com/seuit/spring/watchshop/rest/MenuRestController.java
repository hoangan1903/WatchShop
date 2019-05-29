package com.seuit.spring.watchshop.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.service.FirmService;
import com.seuit.spring.watchshop.service.ModelService;
import com.seuit.spring.watchshop.service.OriginService;

@RestController
@RequestMapping(value="/rest/menu")
public class MenuRestController {
	
	@Autowired
	private FirmService firmService;
	
	@Autowired
	private ModelService modelService;
	
	@Autowired
	private OriginService originService;
	
	@GetMapping()
	private Map<String, Object> getMenu() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String,Object> submapFirm = new HashMap<String, Object>();
		Map<String,Object> submapModel = new HashMap<String, Object>();
		Map<String,Object> submapOrign = new HashMap<String, Object>();
		
		submapFirm = processSubMap("firms", firmService.getListFirm());
		submapOrign = processSubMap("origins", originService.getList());
		submapModel = processSubMap("models", modelService.getList());
		
		map.put("firms", submapFirm);
		map.put("origins", submapOrign);
		map.put("models", submapModel);
		return map;
	}
	private Map<String,Object> processSubMap(String key,List list){
		Map<String,Object> submap = new HashMap<String, Object>();
		submap.put(key, list);
		submap.put("totals",list.size());
		return submap;
	}
}
