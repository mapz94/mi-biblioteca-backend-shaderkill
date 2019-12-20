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
import com.biblioteca.springboot.backend.models.entity.Ciudad;
import com.biblioteca.springboot.backend.models.services.ICiudadService;
import com.biblioteca.springboot.backend.models.services.IPaisService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
// @CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/biblio/ciudades")
public class CiudadRestController {
	
	@Autowired
	private ICiudadService principalService;
	
	@Autowired
	private IPaisService paisService;
	
	
	@GetMapping({"","/"})
	public List<Ciudad> index() {
		return principalService.findAll();
	}
	
	@GetMapping({"/{id}","/{id}/"})
	public ResponseEntity<?> show(@PathVariable Long id) {
		Ciudad objectSearch = null;
		Map<String, Object> response = new HashMap<>();
		try { 
			objectSearch = principalService.findById(id);
		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if ( objectSearch == null ) {
			return GlobalMessage.notFound();
		}
		return new ResponseEntity<Ciudad>(objectSearch, HttpStatus.OK);
	}
	
	@PostMapping({"/","" })
	public ResponseEntity<?> create(@RequestBody Ciudad objectRefered) {
		Ciudad objectCreated = null;
		Map<String, Object> response = new HashMap<>();
		try {
			objectRefered.setPais(paisService.findById(objectRefered.getPais().getId()));
			objectCreated = principalService.save(objectRefered);

		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", objectCreated );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PutMapping({"/{id}","/{id}/"})
	public ResponseEntity<?> update(@RequestBody Ciudad ciudad, @PathVariable Long id) {
		Ciudad ciudadActual = principalService.findById(id);
		Ciudad ciudadUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if ( ciudadActual == null ) {
			return GlobalMessage.notFound();
		}
		try {		
			ciudadActual.setNombre(ciudad.getNombre());
			ciudadActual.setPais(paisService.findById(ciudad.getPais().getId()));
			ciudadUpdated = principalService.save(ciudadActual);
		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", ciudadUpdated);
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
