package com.biblioteca.springboot.backend.models.services;

import java.util.List;
import com.biblioteca.springboot.backend.models.entity.Multa;
import com.biblioteca.springboot.backend.models.entity.Prestamo;

public interface IMultaService {
	
	public List<Multa> findAll();
	
	public Multa findByPrestamo(Prestamo prestamo);
	
	public Multa findById(Long id);
	
	public Multa save(Multa multa);
	
	public void delete(Long id);

}
