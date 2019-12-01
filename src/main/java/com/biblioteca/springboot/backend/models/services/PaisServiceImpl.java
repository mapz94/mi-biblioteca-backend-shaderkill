package com.biblioteca.springboot.backend.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.springboot.backend.models.dao.IPaisDao;
import com.biblioteca.springboot.backend.models.entity.Pais;


@Service
public class PaisServiceImpl implements IPaisService {
	
	@Autowired
	private IPaisDao paisDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pais> findAll() {
		
		return (List<Pais>) paisDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Pais findById(Long id) {
		
		return paisDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Pais save(Pais pais) {
		
		return paisDao.save(pais);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		paisDao.deleteById(id);
	}
	
}
