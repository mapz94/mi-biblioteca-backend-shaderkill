package com.biblioteca.springboot.backend.models.services;

import java.util.List;
import com.biblioteca.springboot.backend.models.entity.Socio;

public interface ISocioService {
	
	public List<Socio> findAll();
	
	public Socio findById(Long id);
	
	public Socio save(Socio user);
	
	public void delete(Long id);

}
