package com.biblioteca.springboot.backend.models.services;

import java.util.List;
import com.biblioteca.springboot.backend.models.entity.EstadoMulta;

public interface IEstadoMultaService {
	
	public List<EstadoMulta> findAll();
	
	public EstadoMulta findById(Long id);
	
	public EstadoMulta save(EstadoMulta estado_multa);
	
	public void delete(Long id);

}
