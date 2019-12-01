package com.biblioteca.springboot.backend.models.dao;

// Imports
import org.springframework.data.repository.CrudRepository;
import com.biblioteca.springboot.backend.models.entity.Ciudad;

/* Interfaz de solicitudes/respuestas, por ahora usamos las 
 * por defecto incluidas en CrudRepository*/
public interface ICiudadDao extends CrudRepository<Ciudad, Long> {

}
