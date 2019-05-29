package com.seuit.spring.watchshop.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Firm;
import com.seuit.spring.watchshop.repository.FirmRepository;

@Service
@Transactional
public class FirmServiceImpl implements FirmService{
	
	 @Autowired
	    private EntityManager entityManager;

	    private Session getSession() {
	        return entityManager.unwrap(Session.class);
	    }
	
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Firm> getListBykeyword(String keyword) {
		// TODO Auto-generated method stub
		 Session session = getSession();
	        String sql = "SELECT e FROM Firm e WHERE e.name like :code";
	        javax.persistence.Query query = session.createQuery(sql);
	        query.setParameter("code", "%" + keyword + "%");
	        return query.getResultList();
	}

	@Override
	public Object getByID(Integer id) {
		// TODO Auto-generated method stub
		return firmRepository.findById(id).isPresent()==true?firmRepository.findById(id).get():null;
	}

	
	
	
}
