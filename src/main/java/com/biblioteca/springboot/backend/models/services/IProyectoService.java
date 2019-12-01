package com.biblioteca.springboot.backend.models.services;

import java.util.List;
import com.biblioteca.springboot.backend.models.entity.Proyecto;

public interface IProyectoService {
	
	public List<Proyecto> findAll();
	
	public Proyecto findById(Long id);
	
	public Proyecto save(Proyecto proyecto);
	
	public void delete(Long id);

}
