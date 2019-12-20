package com.biblioteca.springboot.backend.models.services;

import java.util.List;
import com.biblioteca.springboot.backend.models.entity.Prestamo;
import com.biblioteca.springboot.backend.models.entity.Socio;

public interface IPrestamoService {
	
	public List<Prestamo> findAll();
	
	public Prestamo findById(Long id);
	
	public List<Prestamo> findBySocio(Socio socio);
	
	public Prestamo save(Prestamo prestamo);
	
	public void delete(Long id);

}
