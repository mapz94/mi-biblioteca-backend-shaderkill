package com.biblioteca.springboot.backend.restcontrollers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biblioteca.springboot.backend.GlobalMessage;
import com.biblioteca.springboot.backend.models.entity.DBFiles;
import com.biblioteca.springboot.backend.models.entity.Socio;
import com.biblioteca.springboot.backend.models.services.IUploadFileService;
import com.biblioteca.springboot.backend.models.services.ISocioService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
// @CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/biblio/socios")
public class SocioRestController {

	@Autowired
	private ISocioService socioService;

	@Autowired
	private IUploadFileService uploadService;

	@GetMapping({ "", "/" })
	public ResponseEntity<?> index(@RequestHeader(name = "email") String email,
			@RequestHeader(name = "password") String password ) {
		if (email.equals("administrador@administrador.cl") && password.equals("administrador1234") ) {
			return new ResponseEntity<List<Socio>>(socioService.findAll(), HttpStatus.OK);
		}	
		else {
			return new ResponseEntity<HttpStatus>( HttpStatus.UNAUTHORIZED);
		}
			
	}

	@GetMapping({ "/{id}", "/{id}/" })
	public ResponseEntity<?> show(@PathVariable Long id , @RequestHeader(name = "email") String email,
			@RequestHeader(name = "password") String password ) {
		Socio socioSearch = null;
		Map<String, Object> response = new HashMap<>();
		try {
			if (email.equals("administrador@administrador.cl") && password.equals("administrador1234") ) {
				socioSearch = socioService.findById(id);
				if (socioSearch != null)
					return new ResponseEntity<Socio>(socioSearch, HttpStatus.OK);
				return GlobalMessage.notFound();
			}
			else
				return new ResponseEntity<HttpStatus>( HttpStatus.UNAUTHORIZED);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<?> create(@RequestBody Socio socio) {
		Socio socioCreated = null;
		Map<String, Object> response = new HashMap<>();
		try {
			socioCreated = socioService.save(socio);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El socio ha sido creado con éxito!.");
		response.put("socio", socioCreated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping({ "/login", "/login/" })
	public ResponseEntity<?> login(@RequestHeader(name = "email") String email,
			@RequestHeader(name = "password") String password) {
		Map<String, Object> response = new HashMap<>();
		try {
			Socio socio = socioService.findByEmail(email);
			if (socio == null)
				return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
			if (socio.getPassword().equals(password))
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			return new ResponseEntity<HttpStatus>( HttpStatus.UNAUTHORIZED);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping({ "/{id}", "/{id}/" })
	public ResponseEntity<?> update(@RequestBody Socio socio, @PathVariable Long id) {
		Socio socioActual = socioService.findById(id);
		Socio socioUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if (socioActual == null) {
			return GlobalMessage.notFound();
		}
		try {
			socioActual.setPassword(socio.getPassword());
			socioActual.setEmail(socio.getEmail());
			socioActual.setBiblioteca(socio.getBiblioteca());
			socioActual.setPersona(socio.getPersona());
			socioUpdated = socioService.save(socioActual);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", socioUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping({ "/{id}", "/{id}/" })
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			socioService.delete(id);
		} catch (DataAccessException e) {
			return GlobalMessage.notFound();
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/{id}/upload")
	public ResponseEntity<?> upload(@RequestParam("imgBiblio") MultipartFile archivo, @PathVariable("id") Long id ) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			DBFiles dbFile = uploadService.cargar(archivo);
			
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(dbFile.getId()).toUriString();
			response.put("data",fileDownloadUri);
			
		} catch (FileUploadException e) {
			response.put("error", e);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		 
		
	}

	@GetMapping("/uploads/img/{nombreAvatar}")
	public ResponseEntity<?> show(@PathVariable String id) {
		DBFiles recurso = null;
		try {
			recurso = uploadService.getPath(id);
		} catch (FileNotFoundException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("error", e);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFileName() + "\"");
		
		return new ResponseEntity<DBFiles>(recurso, cabecera, HttpStatus.OK);
	}
}
