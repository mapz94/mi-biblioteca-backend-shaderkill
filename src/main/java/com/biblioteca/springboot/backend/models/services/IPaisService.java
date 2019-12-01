package com.biblioteca.springboot.backend.models.services;

import java.util.List;
import com.biblioteca.springboot.backend.models.entity.Pais;

public interface IPaisService {
	
	public List<Pais> findAll();
	
	public Pais findById(Long id);
	
	public Pais save(Pais pais);
	
	public void delete(Long id);

}
