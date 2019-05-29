package com.seuit.spring.watchshop.rest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.boot.model.source.internal.hbm.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seuit.spring.watchshop.WatchshopApplication;
import com.seuit.spring.watchshop.helper.DAHelper;
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
		submapFirm = DAHelper.getInstance().processSubMapWithTotal("firms", firmService.getListFirm());
		submapOrign = DAHelper.getInstance().processSubMapWithTotal("origins", originService.getList());
		submapModel = DAHelper.getInstance().processSubMapWithTotal("models", modelService.getList());
		map.put("firms", submapFirm);
		map.put("origins", submapOrign);
		map.put("models", submapModel);
		return map;
	}
}
