package com.biblioteca.springboot.backend.restcontrollers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.biblioteca.springboot.backend.models.entity.Multa;
import com.biblioteca.springboot.backend.models.entity.Prestamo;
import com.biblioteca.springboot.backend.models.entity.Socio;
import com.biblioteca.springboot.backend.models.services.IUploadFileService;
import com.biblioteca.springboot.backend.models.services.IEstadoMultaService;
import com.biblioteca.springboot.backend.models.services.IMultaService;
import com.biblioteca.springboot.backend.models.services.IPrestamoService;
import com.biblioteca.springboot.backend.models.services.ISocioService;

@CrossOrigin(origins = "*", allowedHeaders = "*") 
// @CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/biblio/socios")
public class SocioRestController {

	@Autowired
	private ISocioService socioService;
	
	@Autowired
	private IPrestamoService prestamoService;

	@Autowired
	private IUploadFileService uploadService;
	
	@Autowired
	private IMultaService multaService;
	
	@Autowired
	private IEstadoMultaService estadoMultaService;

	@GetMapping({ "", "/" })
	public List<Socio> index() {
		return socioService.findAll();
	}

	@GetMapping({ "/{id}", "/{id}/" })
	public ResponseEntity<?> show(@PathVariable Long id) {
		Socio socioSearch = null;
		Map<String, Object> response = new HashMap<>();
		try {
			socioSearch = socioService.findById(id);
			if (socioSearch != null) 
				return new ResponseEntity<Socio>(socioSearch, HttpStatus.OK);
			else
				return GlobalMessage.notFound();				
		} catch (DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping({ "/{id}/prestamos", "/{id}/prestamos/" })
	public ResponseEntity<?> getPrestamos(@PathVariable Long id) {
		Socio socioSearch = null;
		Map<String, Object> response = new HashMap<>();
		try {
			socioSearch = socioService.findById(id);
			if (socioSearch != null) {
				List<Prestamo> prestamos = prestamoService.findBySocio(socioSearch);
				response.put("prestamos",prestamos);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
			else
				return GlobalMessage.notFound();				
		} catch (DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping({ "/{id}/multas", "/{id}/multas/" })
	public ResponseEntity<?> getMultas(@PathVariable Long id) {
		Socio socioSearch = null;
		Map<String, Object> response = new HashMap<>();
		try {
			socioSearch = socioService.findById(id);
			if (socioSearch != null) {
				List<Multa> multas = multaService.findAll();
				ArrayList<Multa> multasSocio = new ArrayList<Multa>();
				for(int i = 0; i < multas.size(); i++) {
					if(multas.get(i).getPrestamo().getSocio().getId().equals(id) && multas.get(i).getFechaCancelacion().equals(null)) {
						multasSocio.add(multas.get(i));
					}
				}
				response.put("multasSocio",multasSocio);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
			else {
				return GlobalMessage.notFound();
				}
			}
			catch (DataAccessException e) {
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
			if (socio.getPassword().equals(password)) {
				response.put("data",socio.getId());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
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
			Socio socio = socioService.findById(id);
			
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/biblio/socios/download/").path(dbFile.getId()).toUriString();
			response.put("data",fileDownloadUri);
			socio.setImgAvatar(fileDownloadUri);
			socioService.save(socio);
			
		} catch (FileUploadException e) {
			response.put("error", e);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<?> show(@PathVariable String id) {
		DBFiles recurso = null;
		try {
			recurso = uploadService.getPath(id);
		} catch (FileNotFoundException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("error", e);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(recurso.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFileName() + "\"")
				.body(new ByteArrayResource(recurso.getData()));
	}
}
