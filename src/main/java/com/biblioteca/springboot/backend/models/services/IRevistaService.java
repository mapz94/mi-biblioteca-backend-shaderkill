package com.biblioteca.springboot.backend.models.services;

import java.util.List;
import com.biblioteca.springboot.backend.models.entity.Revista;

public interface IRevistaService {
	
	public List<Revista> findAll();
	
	public Revista findById(Long id);
	
	public Revista save(Revista revista);
	
	public void delete(Long id);

}
