package com.biblioteca.springboot.backend.models.services;

import java.util.List;
import com.biblioteca.springboot.backend.models.entity.Libro;

public interface ILibroService {
	
	public List<Libro> findAll();
	
	public Libro findById(Long id);
	
	public Libro save(Libro libro);
	
	public void delete(Long id);

}
