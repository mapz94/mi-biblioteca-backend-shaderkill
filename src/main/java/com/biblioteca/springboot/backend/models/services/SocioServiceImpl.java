package com.biblioteca.springboot.backend.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.springboot.backend.models.dao.ISocioDao;
import com.biblioteca.springboot.backend.models.entity.Socio;


@Service
public class SocioServiceImpl implements ISocioService {
	
	@Autowired
	private ISocioDao socioDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Socio> findAll() {
		
		return (List<Socio>) socioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Socio findById(Long id) {
		
		return socioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Socio save(Socio Socio) {
		
		return socioDao.save(Socio);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		socioDao.deleteById(id);
	}
	
}
