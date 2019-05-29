package com.seuit.spring.watchshop.service;

import java.util.List;
import com.seuit.spring.watchshop.entity.Origin;

public interface OriginService {
	public List<Origin> getList();
	public void create(Origin origin);
	public void deleteById(Integer id);
	public void deleteAll();
	List<Origin> getListBykeyword(String keyword);
	public Object getByID(Integer id);
}
