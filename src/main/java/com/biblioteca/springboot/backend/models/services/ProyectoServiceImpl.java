package com.biblioteca.springboot.backend.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.springboot.backend.models.dao.IProyectoDao;
import com.biblioteca.springboot.backend.models.entity.Proyecto;


@Service
public class ProyectoServiceImpl implements IProyectoService {
	
	@Autowired
	private IProyectoDao proyectoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Proyecto> findAll() {
		
		return (List<Proyecto>) proyectoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Proyecto findById(Long id) {
		
		return proyectoDao.findById(id).orElse(null);
	}

	@Override
	public Proyecto save(Proyecto proyecto) {
		
		return proyectoDao.save(proyecto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		proyectoDao.deleteById(id);
	}
	
}
