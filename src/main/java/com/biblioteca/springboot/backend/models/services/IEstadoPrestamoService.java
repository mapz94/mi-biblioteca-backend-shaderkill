package com.biblioteca.springboot.backend.models.services;

import java.util.List;
import com.biblioteca.springboot.backend.models.entity.EstadoPrestamo;

public interface IEstadoPrestamoService {
	
	public List<EstadoPrestamo> findAll();
	
	public EstadoPrestamo findById(Long id);
	
	public EstadoPrestamo save(EstadoPrestamo estado_prestamo);
	
	public void delete(Long id);

}
