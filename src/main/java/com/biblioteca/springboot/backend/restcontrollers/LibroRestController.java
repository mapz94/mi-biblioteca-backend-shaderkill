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
//import org.springframework.web.client.RestTemplate; For calling another endpoint.

import com.biblioteca.springboot.backend.GlobalMessage;
import com.biblioteca.springboot.backend.models.entity.Libro;
import com.biblioteca.springboot.backend.models.services.ILibroService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
// @CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/biblio/libros")
public class LibroRestController {
	
	@Autowired
	private ILibroService principalService;
	
	
	@GetMapping({"","/"})
	public List<Libro> index() {
		return principalService.findAll();
	}
	
	@GetMapping({"/{id}","/{id}/"})
	public ResponseEntity<?> show(@PathVariable Long id) {
		Libro objectSearch = null;
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
		return new ResponseEntity<Libro>(objectSearch, HttpStatus.OK);
	}
	
	@PostMapping({"/","" })
	public ResponseEntity<?> create(@RequestBody Libro objectRefered) {
		Libro objectCreated = null;
		Map<String, Object> response = new HashMap<>();
		try {
			objectCreated = principalService.save(objectRefered);

		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", objectCreated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	
	
	@PutMapping({"/{id}","/{id}/"})
	public ResponseEntity<?> update(@RequestBody Libro libro, @PathVariable Long id) {
		Libro libroActual = principalService.findById(id);
		Libro libroUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if ( libroActual == null ) {
			return GlobalMessage.notFound();
		}
		try {
			libroActual.setIsbn(libro.getIsbn());			
			libroActual.setEditorial(libro.getEditorial());	
			libroActual.setAutor(libro.getAutor());		
			libroUpdated = principalService.save(libroActual);
		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", libroUpdated);
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
