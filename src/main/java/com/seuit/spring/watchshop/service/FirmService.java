package com.seuit.spring.watchshop.service;

import java.util.List;

import com.seuit.spring.watchshop.entity.Firm;

public interface FirmService {
	public List<Firm> getListFirm();
	public void createFirm(Firm firm);
	public void deleteFirmById(Integer id);
	public void deleteAllFirm();
	public Long getTotalFirm();
}
