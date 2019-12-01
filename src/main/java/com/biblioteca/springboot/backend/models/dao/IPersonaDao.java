package com.biblioteca.springboot.backend.models.dao;

// Imports
import org.springframework.data.repository.CrudRepository;
import com.biblioteca.springboot.backend.models.entity.Persona;

/* Interfaz de solicitudes/respuestas, por ahora usamos las 
 * por defecto incluidas en CrudRepository*/
public interface IPersonaDao extends CrudRepository<Persona, Long> {

}
