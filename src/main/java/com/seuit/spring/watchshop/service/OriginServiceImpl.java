package com.seuit.spring.watchshop.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Origin;
import com.seuit.spring.watchshop.repository.OriginRepository;

@Service
@Transactional
public class OriginServiceImpl implements OriginService {
	
	@Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }
	
	@Autowired
	private OriginRepository originRepository;

	@Override
	public List<Origin> getList() {
		// TODO Auto-generated method stub
		return originRepository.findAll();
	}

	@Override
	public void create(Origin origin) {
		// TODO Auto-generated method stub
		originRepository.save(origin);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		originRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		originRepository.deleteAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Origin> getListBykeyword(String keyword) {
		// TODO Auto-generated method stub
		Session session = getSession();
        String sql = "SELECT e FROM Origin e WHERE e.name like :code";
        javax.persistence.Query query = session.createQuery(sql);
        query.setParameter("code", "%" + keyword + "%");
        return query.getResultList();
	}
	
	@Override
	public Object getByID(Integer id) {
		// TODO Auto-generated method stub
		return originRepository.findById(id).isPresent()==true?originRepository.findById(id).get():null;
	}
	
}
