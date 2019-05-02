package com.seuit.spring.watchshop.service;

import java.util.List;

import com.seuit.spring.watchshop.entity.Banner;

public interface BannerService {
	public List<Banner> getListBanner();
	public void createBanner(Banner banner);
	public void deleteBannerById(Integer id);
	public void deleteAllBanner();
	public Long getTotalBanners();
}
