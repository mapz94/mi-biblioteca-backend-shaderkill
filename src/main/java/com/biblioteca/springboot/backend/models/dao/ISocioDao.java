package com.biblioteca.springboot.backend.models.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
// Imports
import org.springframework.stereotype.Repository;

import com.biblioteca.springboot.backend.models.entity.Socio;

/* Interfaz de solicitudes/respuestas, por ahora usamos las 
 * por defecto incluidas en CrudRepository*/
@Repository
public interface ISocioDao extends JpaRepository<Socio, Long> {
	
	@Query("SELECT d FROM Socio d WHERE email =?1 AND password = ?2")
	public Socio validateSocio(String email, String password);

}
