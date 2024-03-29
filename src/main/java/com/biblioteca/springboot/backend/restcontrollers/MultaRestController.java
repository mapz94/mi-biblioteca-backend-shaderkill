package com.biblioteca.springboot.backend.restcontrollers;

import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.biblioteca.springboot.backend.GlobalMessage;
import com.biblioteca.springboot.backend.models.entity.Multa;
import com.biblioteca.springboot.backend.models.entity.Prestamo;
import com.biblioteca.springboot.backend.models.services.IEstadoMultaService;
import com.biblioteca.springboot.backend.models.services.IEstadoPrestamoService;
import com.biblioteca.springboot.backend.models.services.IMultaService;
import com.biblioteca.springboot.backend.models.services.IPrestamoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
// @CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/biblio/multas")
public class MultaRestController {
	
	@Autowired
	private IMultaService principalService;
	
	@Autowired
	private IPrestamoService prestamoService;
	
	@Autowired
	private IEstadoMultaService estadoMultaService;
	
	@Autowired
	private IEstadoPrestamoService estadoPrestamoService;
	
	
	@GetMapping({"","/"})
	public List<Multa> index() {
		return principalService.findAll();
	}
	
	@GetMapping({"/generar","/generar/"})
	public ResponseEntity<?> generar() {
		ArrayList<Multa> multas = new ArrayList<Multa>();
		Map<String, Object> response = new HashMap<>();
		try {
			List<Prestamo> prestamos = prestamoService.findAll();
			for (int i = 0; i < prestamos.size(); i++) {
				Prestamo prestamo = prestamos.get(i);
				if (prestamo.getEstadoPrestamo().getId() == (long) 1) {
					if(prestamo.getFechaVencimiento().compareTo(Calendar.getInstance().getTime()) < 0) {
						Multa multa = new Multa();
						multa.setPrestamo(prestamo);
						multa.setMonto(2000);
						Long estado = (long) 1;
						multa.setEstadoMulta(estadoMultaService.findById(estado));
						Multa multaCreated = principalService.save(multa);				
						multas.add(multaCreated);
					}
				}
			}
			
		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Multa>>(multas, HttpStatus.OK);
	}
	
	
	@GetMapping({"/{id}","/{id}/"})
	public ResponseEntity<?> show(@PathVariable Long id) { 
		Multa objectSearch = null;
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
		return new ResponseEntity<Multa>(objectSearch, HttpStatus.OK);
	}
	
	@GetMapping({"/{id}/multas","/{id}/multas/"})
	public ResponseEntity<?> getPrestamos(@PathVariable Long idPrestamo) {
		Multa multas = null;
		Map<String, Object> response = new HashMap<>();
		try { 
			
			multas = principalService.findByPrestamo(prestamoService.findById(idPrestamo));
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la busqueda en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if ( multas == null ) {
			return GlobalMessage.notFound();
		}
		return new ResponseEntity<Multa>(multas, HttpStatus.OK);
	}
	
	
	@PostMapping({"/","" })
	public ResponseEntity<?> create(@RequestParam Long prestamo) {
		Multa objectCreated = new Multa();
		Map<String, Object> response = new HashMap<>();
		try {
			objectCreated.setPrestamo(prestamoService.findById(prestamo));
			objectCreated.setMonto(2000);
			Long estado = (long) 1;
			objectCreated.setEstadoMulta(estadoMultaService.findById(estado));
			//objectCreated.setFechaCancelacion();
			Multa multa = principalService.save(objectCreated);
			
			response.put("multa", multa);
		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", objectCreated );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping({"/cancela/{id}","/cancela/{id}/" })
	public ResponseEntity<?> cancelaMulta(@PathVariable Long id) {
		Multa objectCreated = null;
		Map<String, Object> response = new HashMap<>();
		try {
			objectCreated = principalService.findById(id);
			Calendar now = Calendar.getInstance();
			objectCreated.setFechaCancelacion(now.getTime());
			Long estado = (long) 2;
			objectCreated.setEstadoMulta(estadoMultaService.findById(estado));
			objectCreated.getPrestamo().setEstadoPrestamo(estadoPrestamoService.findById((long)2));
			Multa multa = principalService.save(objectCreated);
			response.put("multa", multa);
		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", objectCreated );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PutMapping({"/{id}","/{id}/"})
	public ResponseEntity<?> update(@RequestBody Multa multa, @PathVariable Long id) {
		Multa multaActual = principalService.findById(id);
		Multa multaUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if ( multaActual == null ) {
			return GlobalMessage.notFound();
		}
		try {		
			multaActual.setPrestamo(multa.getPrestamo());
			multaActual.setMonto(multa.getMonto());
			multaActual.setEstadoMulta(multa.getEstadoMulta());
			multaActual.setFechaCancelacion(multa.getFechaCancelacion());
			multaUpdated = principalService.save(multaActual);
		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		response.put("data", multaUpdated);
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
