package com.biblioteca.springboot.backend.models.dao;

// Imports
import org.springframework.data.repository.CrudRepository;
import com.biblioteca.springboot.backend.models.entity.Prestamo;

/* Interfaz de solicitudes/respuestas, por ahora usamos las 
 * por defecto incluidas en CrudRepository*/
public interface IPrestamoDao extends CrudRepository<Prestamo, Long> {

}
