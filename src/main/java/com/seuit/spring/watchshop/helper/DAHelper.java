package com.seuit.spring.watchshop.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.multipart.MultipartFile;

import com.seuit.spring.watchshop.entity.Product;
import com.seuit.spring.watchshop.entity.User;

public class DAHelper {
	private static final DAHelper instance = new DAHelper();
	private DAHelper() {};
	public static DAHelper getInstance() {
			return instance;
	}
	
	public Map<String, Object> processSubMapWithTotal(String key, List list) {
		// TODO Auto-generated method stub
		Map<String,Object> submap = new HashMap<String, Object>();
		submap.put(key, list);
		submap.put("totals",list.size());
		return submap;
	}
	
	public Map<String, Object> processSubMapWithTotalLimit(String key,List list,Integer sizeLimit) {
		Map<String, Object> submap = new HashMap<String, Object>();
		Integer size = list.size();
		if (size > sizeLimit) {
			list = list.subList(0, sizeLimit);
		}
		submap.put(key, list);
		submap.put("totals", list.size());
		return submap;
	}	
}
