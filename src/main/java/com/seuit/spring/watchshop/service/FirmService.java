package com.seuit.spring.watchshop.service;

import java.util.List;

import com.seuit.spring.watchshop.entity.Firm;
import com.seuit.spring.watchshop.entity.Model;
import com.seuit.spring.watchshop.entity.Order;

public interface FirmService {
	public List<Firm> getListFirm(Integer pageId,Integer size);
	public void createFirm(Firm firm);
	public void deleteFirmById(Integer id);
	public void deleteAllFirm();
	public Long getTotalFirm();
	List<Firm> getListBykeyword(String keyword);
	public Object getByID(Integer id);
	public List<Firm> pagination(Integer pageId, Integer size,List<Firm> list);
	public void edit(Firm firm);
}
