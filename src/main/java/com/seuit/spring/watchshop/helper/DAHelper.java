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
	
	@Autowired
	private Environment env;
	
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
	
	public File convert(MultipartFile file) {
		File convFile = new File(file.getOriginalFilename());
		try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
			return convFile;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void createLoggerMessager(String nameClass,String messager) {
		Logger logger = Logger.getLogger(nameClass);
		logger.info(messager);
	}
	
	public SimpleMailMessage constructEmail(String subject, String body, User user) {
		// TODO Auto-generated method stub
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(user.getEmail());
		email.setFrom(env.getProperty("email.support.from"));
		return email;
	}
	
}
