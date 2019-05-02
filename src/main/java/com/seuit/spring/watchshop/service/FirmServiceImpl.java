package com.seuit.spring.watchshop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Firm;
import com.seuit.spring.watchshop.repository.FirmRepository;

@Service
@Transactional
public class FirmServiceImpl implements FirmService{
	@Autowired
	private FirmRepository firmRepository;

	@Override
	public List<Firm> getListFirm() {
		// TODO Auto-generated method stub
		return firmRepository.findAll();
	}

	@Override
	public void createFirm(Firm firm) {
		// TODO Auto-generated method stub
		firmRepository.save(firm);
	}

	@Override
	public void deleteFirmById(Integer id) {
		// TODO Auto-generated method stub
		firmRepository.deleteById(id);
	}

	@Override
	public void deleteAllFirm() {
		// TODO Auto-generated method stub
		firmRepository.deleteAll();
	}

	@Override
	public Long getTotalFirm() {
		// TODO Auto-generated method stub
		return firmRepository.count();
	}

	
	
	
}
