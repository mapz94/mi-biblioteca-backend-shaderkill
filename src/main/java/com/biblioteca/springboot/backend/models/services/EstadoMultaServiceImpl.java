package com.biblioteca.springboot.backend.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.springboot.backend.models.dao.IEstadoMultaDao;
import com.biblioteca.springboot.backend.models.entity.EstadoMulta;


@Service
public class EstadoMultaServiceImpl implements IEstadoMultaService {
	
	@Autowired
	private IEstadoMultaDao estado_multa_Dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<EstadoMulta> findAll() {
		
		return (List<EstadoMulta>) estado_multa_Dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public EstadoMulta findById(Long id) {
		
		return estado_multa_Dao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public EstadoMulta save(EstadoMulta estado_multa) {
		
		return estado_multa_Dao.save(estado_multa);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		estado_multa_Dao.deleteById(id);
	}
	
}
