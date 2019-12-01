package com.biblioteca.springboot.backend.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.springboot.backend.models.dao.IRevistaDao;
import com.biblioteca.springboot.backend.models.entity.Revista;


@Service
public class RevistaServiceImpl implements IRevistaService {
	
	@Autowired
	private IRevistaDao revistaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Revista> findAll() {
		
		return (List<Revista>) revistaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Revista findById(Long id) {
		
		return revistaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Revista save(Revista revista) {
		
		return revistaDao.save(revista);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		revistaDao.deleteById(id);
	}
	
}
