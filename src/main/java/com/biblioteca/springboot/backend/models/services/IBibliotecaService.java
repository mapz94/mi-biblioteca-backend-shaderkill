package com.biblioteca.springboot.backend.models.services;

import java.util.List;
import com.biblioteca.springboot.backend.models.entity.Biblioteca;

public interface IBibliotecaService {
	
	public List<Biblioteca> findAll();
	
	public Biblioteca findById(Long id);
	
	public Biblioteca save(Biblioteca biblioteca);
	
	public void delete(Long id);

}
