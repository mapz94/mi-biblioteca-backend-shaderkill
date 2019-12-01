package com.biblioteca.springboot.backend.models.services;

import java.util.List;
import com.biblioteca.springboot.backend.models.entity.Ciudad;

public interface ICiudadService {
	
	public List<Ciudad> findAll();
	
	public Ciudad findById(Long id);
	
	public Ciudad save(Ciudad ciudad);
	
	public void delete(Long id);

}
