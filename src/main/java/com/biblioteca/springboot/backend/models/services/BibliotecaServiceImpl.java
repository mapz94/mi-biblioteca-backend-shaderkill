package com.biblioteca.springboot.backend.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.springboot.backend.models.dao.IBibliotecaDao;
import com.biblioteca.springboot.backend.models.entity.Biblioteca;


@Service
public class BibliotecaServiceImpl implements IBibliotecaService {
	
	@Autowired
	private IBibliotecaDao bibliotecaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Biblioteca> findAll() {
		
		return (List<Biblioteca>) bibliotecaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Biblioteca findById(Long id) {
		
		return bibliotecaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Biblioteca save(Biblioteca biblioteca) {
		
		return bibliotecaDao.save(biblioteca);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		bibliotecaDao.deleteById(id);
	}
	
}
