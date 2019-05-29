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

import com.seuit.spring.watchshop.entity.Banner;
import com.seuit.spring.watchshop.helper.DAHelper;
import com.seuit.spring.watchshop.service.BannerService;

@RestController
@RequestMapping(value="/rest")
public class BannerRestController {
	@Autowired
	private BannerService bannerService;
	
	@GetMapping(value = "/banners")
	private Map<String,Object> getListBanner(){
		return DAHelper.getInstance().processSubMapWithTotal("banners", bannerService.getListBanner());
	}
	@PostMapping(value="/banners")
	private void createBanner(@Valid @RequestBody Banner banner) {
		bannerService.createBanner(banner);
	}
	
	@DeleteMapping(value="/banners/{id}")
	private void deleteBannerById(@PathVariable(name = "id") Integer id) {
		bannerService.deleteBannerById(id);
	}
	
	@DeleteMapping(value="/banners")
	private void deleteAllBanner() {
		bannerService.deleteAllBanner();
	}
}
