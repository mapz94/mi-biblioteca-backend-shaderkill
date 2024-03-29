package com.biblioteca.springboot.backend.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.springboot.backend.models.dao.IMultaDao;
import com.biblioteca.springboot.backend.models.entity.Multa;
import com.biblioteca.springboot.backend.models.entity.Prestamo;


@Service
public class MultaServiceImpl implements IMultaService {
	
	@Autowired
	private IMultaDao multaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Multa> findAll() {
		
		return (List<Multa>) multaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Multa findById(Long id) {
		
		return multaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Multa save(Multa multa) {
		
		return multaDao.save(multa);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		multaDao.deleteById(id);
	}

	@Override
	public Multa findByPrestamo(Prestamo prestamo) {
		return multaDao.findByPrestamo(prestamo);
	}

	
}
