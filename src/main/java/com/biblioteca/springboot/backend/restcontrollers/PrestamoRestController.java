package com.biblioteca.springboot.backend.restcontrollers;

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
import com.biblioteca.springboot.backend.models.entity.EstadoMulta;
import com.biblioteca.springboot.backend.models.entity.MaterialBibliografico;
import com.biblioteca.springboot.backend.models.entity.Multa;
import com.biblioteca.springboot.backend.models.entity.Prestamo;
import com.biblioteca.springboot.backend.models.entity.Socio;
import com.biblioteca.springboot.backend.models.services.IPrestamoService;
import com.biblioteca.springboot.backend.models.services.ISocioService;
import com.biblioteca.springboot.backend.models.services.IEstadoMultaService;
import com.biblioteca.springboot.backend.models.services.IMaterialBibliograficoService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
// @CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/biblio/prestamos")
public class PrestamoRestController {
	
	@Autowired
	private IPrestamoService principalService;
	
	/*@Autowired
	private IEstadoMultaService estadoMultaService;*/
	
	@Autowired
	private ISocioService socioService;
	
	@Autowired
	private IMaterialBibliograficoService materialService;
	
	
	@GetMapping({"","/"})  
	public List<Prestamo> index() {
		return principalService.findAll();
	}
	 
	@GetMapping({"/{id}/isDue","/{id}/isDue/"})
	public ResponseEntity<?> isDue(@PathVariable Long id) {
		Prestamo objectSearch = null;
		Map<String, Object> response = new HashMap<>();
		try { 
			objectSearch = principalService.findById(id);
			if(objectSearch.getFechaVencimiento().compareTo(Calendar.getInstance().getTime()) < 0){
				response.put("isDue", true );
				/*Socio socio = socioService.findById(objectSearch.getId());
				
				Multa multaActual = new Multa();
				multaActual.setPrestamo(objectSearch);
				multaActual.setMonto(2000);
				Long estado = (long) 1;
				multaActual.setEstadoMulta(estadoMultaService.findById(estado));
				multaActual.setFechaCancelacion(); */
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
			else {
				response.put("isDue", false );
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping({"/{id}","/{id}/"})
	public ResponseEntity<?> show(@PathVariable Long id) {
		Prestamo objectSearch = null;
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
		return new ResponseEntity<Prestamo>(objectSearch, HttpStatus.OK);
	}
	
	@PostMapping({"/","" })
	public ResponseEntity<?> create(@RequestParam Long socioId, @RequestParam Long materialId ) {
		Prestamo objectRefered = new Prestamo();
		Prestamo objectCreated = null;
		Map<String, Object> response = new HashMap<>();
		try {
			objectRefered.setSocio(socioService.findById(socioId));
			objectRefered.setMaterialBibliografico(materialService.findById(materialId));
			Calendar now = Calendar.getInstance();
			objectRefered.setFechaPrestamo(now.getTime());
			now.add(Calendar.DATE, 7);
			objectRefered.setFechaVencimiento(now.getTime());
			objectCreated = principalService.save(objectRefered);

		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", objectCreated );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping({"/entrega/{id}","/entrega/{id}/" })
	public ResponseEntity<?> entrega(@PathVariable Long id ) {
		Prestamo prestamoActual = principalService.findById(id);
		Prestamo prestamoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if ( prestamoActual == null ) {
			return GlobalMessage.notFound();
		}
		try {
			Calendar now = Calendar.getInstance();
			prestamoActual.setFechaEntrega(now.getTime());
			prestamoUpdated = principalService.save(prestamoActual);
			response.put("data",prestamoActual.getFechaEntrega());
			
		}
		catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@PutMapping({"/{id}","/{id}/"})
	public ResponseEntity<?> update(@RequestBody Prestamo prestamo, @PathVariable Long id) {
		Prestamo prestamoActual = principalService.findById(id);
		Prestamo prestamoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if ( prestamoActual == null ) {
			return GlobalMessage.notFound();
		}
		try {		
			prestamoActual.setSocio(prestamo.getSocio());
			prestamoActual.setMaterialBibliografico(prestamo.getMaterialBibliografico());
			prestamoActual.setFechaPrestamo(prestamo.getFechaPrestamo());
			prestamoActual.setFechaVencimiento(prestamo.getFechaVencimiento());
			prestamoActual.setFechaEntrega(prestamo.getFechaEntrega());
			prestamoActual.setPrestamo(prestamo.getPrestamo());
			prestamoUpdated = principalService.save(prestamoActual);
		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", prestamoUpdated);
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
