package com.seuit.spring.watchshop.service;

import java.util.List;

import com.seuit.spring.watchshop.entity.Firm;
import com.seuit.spring.watchshop.entity.Model;

public interface FirmService {
	public List<Firm> getListFirm();
	public void createFirm(Firm firm);
	public void deleteFirmById(Integer id);
	public void deleteAllFirm();
	public Long getTotalFirm();
	List<Firm> getListBykeyword(String keyword);
	public Object getByID(Integer id);
}
