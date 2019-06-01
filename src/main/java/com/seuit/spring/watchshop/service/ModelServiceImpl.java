package com.seuit.spring.watchshop.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Firm;
import com.seuit.spring.watchshop.entity.Model;
import com.seuit.spring.watchshop.repository.ModelRepository;

@Service
@Transactional
public class ModelServiceImpl implements ModelService {
	
	@Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }
	
	@Autowired
	private ModelRepository modelRepository;

	@Override
	public List<Model> getList(Integer pageId,Integer size) {
		// TODO Auto-generated method stub
		if(pageId==null && size == null) {
			return modelRepository.findAll();
		}
		return this.pagination(pageId, size, modelRepository.findAll());
	}

	@Override
	public void create(Model model) {
		// TODO Auto-generated method stub
		modelRepository.save(model);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		modelRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		modelRepository.deleteAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Model> getListBykeyword(String keyword) {
		// TODO Auto-generated method stub
		Session session = getSession();
        String sql = "SELECT e FROM Model e WHERE e.name like :code";
        javax.persistence.Query query = session.createQuery(sql);
        query.setParameter("code", "%" + keyword + "%");
        return query.getResultList();
	}

	@Override
	public Object getByID(Integer id) {
		// TODO Auto-generated method stub
		return modelRepository.findById(id).isPresent()==true?modelRepository.findById(id).get():null;
	}

	@Override
	public List<Model> pagination(Integer pageId, Integer size, List<Model> list) {
		// TODO Auto-generated method stub
		Integer start = pageId*size;
		return list.stream().filter((c)->list.indexOf(c)>=start).limit(size).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void edit(Model model) {
		// TODO Auto-generated method stub
		if(model.getId()==null) {
			return;
		}
		Model modelInDB = modelRepository.getOne(model.getId());
		modelInDB.setName(model.getName());
		modelRepository.save(modelInDB);
	}
	
}
