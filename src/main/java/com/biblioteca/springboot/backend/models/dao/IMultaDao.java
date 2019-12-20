package com.biblioteca.springboot.backend.models.dao;

import java.util.List;

// Imports
import org.springframework.data.repository.CrudRepository;
import com.biblioteca.springboot.backend.models.entity.Multa;
import com.biblioteca.springboot.backend.models.entity.Prestamo;
import com.biblioteca.springboot.backend.models.entity.Socio;

/* Interfaz de solicitudes/respuestas, por ahora usamos las 
 * por defecto incluidas en CrudRepository*/
public interface IMultaDao extends CrudRepository<Multa, Long> {
	public List<Multa> findBySocio(Socio socio);
}
