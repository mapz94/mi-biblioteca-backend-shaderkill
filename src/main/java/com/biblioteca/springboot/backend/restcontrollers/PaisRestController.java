package com.biblioteca.springboot.backend.restcontrollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.biblioteca.springboot.backend.GlobalMessage;
import com.biblioteca.springboot.backend.models.entity.Pais;
import com.biblioteca.springboot.backend.models.services.IPaisService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
// @CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/biblio/paises")
public class PaisRestController {
	
	@Autowired
	private IPaisService principalService;
	
	
	@GetMapping({"","/"})
	public List<Pais> index() {
		return principalService.findAll();
	}
	
	@GetMapping({"/{id}","/{id}/"})
	public ResponseEntity<?> show(@PathVariable Long id) {
		Pais objectSearch = null;
		Map<String, Object> response = new HashMap<>();
		try { 
			objectSearch = principalService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la busqueda en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if ( objectSearch == null ) {
			return GlobalMessage.notFound();
		}
		return new ResponseEntity<Pais>(objectSearch, HttpStatus.OK);
	}
	
	@PostMapping({"/","" })
	public ResponseEntity<?> create(@RequestBody Pais objectRefered) {
		Pais objectCreated = null;
		Map<String, Object> response = new HashMap<>();
		try {
			objectCreated = principalService.save(objectRefered);

		} catch(DataAccessException e) {
			return GlobalMessage.internalServerError();
		}
		response.put("data", objectCreated );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PutMapping({"/{id}","/{id}/"})
	public ResponseEntity<?> update(@RequestBody Pais pais, @PathVariable Long id) {
		Pais paisActual = principalService.findById(id);
		Pais paisUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if ( paisActual == null ) {
			return GlobalMessage.notFound();
		}
		try {		
			paisActual.setNombre(pais.getNombre());
			paisUpdated = principalService.save(paisActual);
		} catch(DataAccessException e) {
			return GlobalMessage.internalServerError();
		}
		response.put("data", paisUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping({"/{id}","/{id}/"})
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			principalService.delete(id);
		} catch(DataAccessException e) {
			return GlobalMessage.notFound();
		} 
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
