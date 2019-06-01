package com.seuit.spring.watchshop.service;

import java.util.List;

import com.seuit.spring.watchshop.entity.Firm;
import com.seuit.spring.watchshop.entity.Model;

public interface ModelService {
	public List<Model> getList(Integer pageId,Integer size);
	public void create(Model model);
	public void deleteById(Integer id);
	public void deleteAll();
	List<Model> getListBykeyword(String keyword);
	public Object getByID(Integer id);
	public List<Model> pagination(Integer pageId, Integer size,List<Model> list);
	public void edit(Model model);
}
