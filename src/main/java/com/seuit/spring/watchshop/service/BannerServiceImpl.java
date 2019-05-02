package com.seuit.spring.watchshop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Banner;
import com.seuit.spring.watchshop.repository.BannerRepository;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerRepository bannerRepository;
	
	@Override
	public List<Banner> getListBanner() {
		// TODO Auto-generated method stub
		return bannerRepository.findAll();
	}

	@Override
	public void createBanner(Banner banner) {
		// TODO Auto-generated method stub
		bannerRepository.save(banner);
	}

	@Override
	public void deleteBannerById(Integer id) {
		// TODO Auto-generated method stub
		bannerRepository.deleteById(id);
	}

	@Override
	public void deleteAllBanner() {
		// TODO Auto-generated method stub
		bannerRepository.deleteAll();
	}

	@Override
	public Long getTotalBanners() {
		// TODO Auto-generated method stub
		return bannerRepository.count();
	}
	
	

}
