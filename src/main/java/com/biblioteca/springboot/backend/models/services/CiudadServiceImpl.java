package com.biblioteca.springboot.backend.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.springboot.backend.models.dao.ICiudadDao;
import com.biblioteca.springboot.backend.models.entity.Ciudad;


@Service
public class CiudadServiceImpl implements ICiudadService {
	
	@Autowired
	private ICiudadDao ciudadDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Ciudad> findAll() {
		
		return (List<Ciudad>) ciudadDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Ciudad findById(Long id) {
		
		return ciudadDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Ciudad save(Ciudad ciudad) {
		
		return ciudadDao.save(ciudad);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		ciudadDao.deleteById(id);
	}
	
}
