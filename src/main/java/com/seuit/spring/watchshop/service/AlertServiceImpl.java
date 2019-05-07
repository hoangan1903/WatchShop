package com.seuit.spring.watchshop.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Alert;
import com.seuit.spring.watchshop.entity.CustomUserDetail;
import com.seuit.spring.watchshop.repository.AlertRepository;

@Service
@Transactional
public class AlertServiceImpl implements AlertService {

	@Autowired
	private AlertRepository alertRepository;

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public void create(Alert alert) {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();
		if (user.getRoles().stream().filter((r) -> r.getName() == "admin").findFirst().isPresent() == true) {
			alert.setUser(user);
			alertRepository.save(alert);
		}
	}

	@Override
	public Map<String, Object> getListAlert() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<Alert> alerts = alertRepository.findAll().stream()
				.sorted(Comparator.comparing(Alert::getStatus)).collect(Collectors.toList());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totals", alerts.size());
		map.put("alerts", alerts);
		return map;
	}

	@Override
	public Map<String, Object> getListAlertNew() {
		// TODO Auto-generated method stub
		List<Alert> alerts = alertRepository.findAll().stream().filter((a) -> a.getStatus() == 0)
				.sorted(Comparator.comparing(Alert::getCreateAt).reversed()).collect(Collectors.toList());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totals", alerts.size());
		map.put("alerts", alerts);
		return map;
	}

	@Override
	public void readNewAlert() {
		// TODO Auto-generated method stub
		alertRepository.findAll().stream().forEach(a->a.setStatus(1));
	}

}
