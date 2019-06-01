package com.seuit.spring.watchshop.service;

import java.util.List;

import com.seuit.spring.watchshop.entity.Firm;
import com.seuit.spring.watchshop.entity.Origin;

public interface OriginService {
	public List<Origin> getList(Integer pageId,Integer size);
	public void create(Origin origin);
	public void deleteById(Integer id);
	public void deleteAll();
	List<Origin> getListBykeyword(String keyword);
	public Object getByID(Integer id);
	public List<Origin> pagination(Integer pageId, Integer size,List<Origin> list);
	public void edit(Origin origin);
}
