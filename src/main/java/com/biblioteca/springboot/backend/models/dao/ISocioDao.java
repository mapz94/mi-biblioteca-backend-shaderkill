package com.biblioteca.springboot.backend.models.dao;

import org.springframework.data.jdbc.repository.query.Query;
// Imports
import org.springframework.data.repository.CrudRepository;
import com.biblioteca.springboot.backend.models.entity.Socio;

/* Interfaz de solicitudes/respuestas, por ahora usamos las 
 * por defecto incluidas en CrudRepository*/
public interface ISocioDao extends CrudRepository<Socio, Long> {
	
	@Query("SELECT d FROM Socio d WHERE email =?1 AND password = ?2")
	public boolean validateSocio(String email, String password);

}
