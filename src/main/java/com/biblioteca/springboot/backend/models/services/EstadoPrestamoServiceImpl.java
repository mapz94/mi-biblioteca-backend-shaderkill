package com.biblioteca.springboot.backend.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.springboot.backend.models.dao.IEstadoPrestamoDao;
import com.biblioteca.springboot.backend.models.entity.EstadoPrestamo;


@Service
public class EstadoPrestamoServiceImpl implements IEstadoPrestamoService {
	
	@Autowired
	private IEstadoPrestamoDao estado_prestamo_Dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<EstadoPrestamo> findAll() {
		
		return (List<EstadoPrestamo>) estado_prestamo_Dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public EstadoPrestamo findById(Long id) {
		
		return estado_prestamo_Dao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public EstadoPrestamo save(EstadoPrestamo estado_prestamo) {
		
		return estado_prestamo_Dao.save(estado_prestamo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		estado_prestamo_Dao.deleteById(id);
	}
	
}
