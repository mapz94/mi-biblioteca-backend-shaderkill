package com.biblioteca.springboot.backend.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.springboot.backend.models.dao.IPrestamoDao;
import com.biblioteca.springboot.backend.models.entity.Prestamo;
import com.biblioteca.springboot.backend.models.entity.Socio;


@Service
public class PrestamoServiceImpl implements IPrestamoService {
	
	@Autowired
	private IPrestamoDao prestamoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> findAll() {
		return (List<Prestamo>) prestamoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Prestamo findById(Long id) {
		return prestamoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Prestamo save(Prestamo prestamo) {
		return prestamoDao.save(prestamo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		prestamoDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> findBySocio(Socio socio) {
		return prestamoDao.findBySocio(socio);
	}

}
