package com.biblioteca.springboot.backend.restcontrollers;

import java.io.FileNotFoundException;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biblioteca.springboot.backend.GlobalMessage;
import com.biblioteca.springboot.backend.models.entity.DBFiles;
import com.biblioteca.springboot.backend.models.entity.MaterialBibliografico;
import com.biblioteca.springboot.backend.models.entity.Prestamo;
import com.biblioteca.springboot.backend.models.services.IUploadFileService;
import com.biblioteca.springboot.backend.models.services.ICategoriaService;
import com.biblioteca.springboot.backend.models.services.IMaterialBibliograficoService;
import com.biblioteca.springboot.backend.models.services.IPrestamoService;
import com.biblioteca.springboot.backend.models.services.ISocioService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
// @CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/biblio/materialbibliografico")
public class MaterialBibliograficoRestController {
	
	@Autowired
	private IMaterialBibliograficoService principalService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	@Autowired
	private ISocioService socioService;
	
	@Autowired
	private IPrestamoService prestamoService;
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@GetMapping({"","/"})
	public List<MaterialBibliografico> index() {
		return principalService.findAll();
	}
	
	@GetMapping({"/{id}","/{id}/"})
	public ResponseEntity<?> show(@PathVariable Long id) {
		MaterialBibliografico objectSearch = null;
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
		return new ResponseEntity<MaterialBibliografico>(objectSearch, HttpStatus.OK);
	}
	
	@PostMapping({"/","" })
	public ResponseEntity<?> create(@RequestBody MaterialBibliografico objectRefered) {
		MaterialBibliografico objectCreated = null;
		Map<String, Object> response = new HashMap<>();
		try {
			//objectRefered.setCategoria(categoriaService.findById(objectRefered.getCategoria().getId()));
			objectCreated = principalService.save(objectRefered);
			
		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//response.put("mensaje", "El material bibliografico ha sido creado con éxito!.");
		response.put("data", objectCreated );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping({"/{id}/user/{socioId}"})
	public ResponseEntity<?> createPrestamo(@PathVariable Long id, @PathVariable Long socioId ) {
		Prestamo objectRefered = new Prestamo();
		Prestamo objectCreated = null;
		Map<String, Object> response = new HashMap<>();
		try {
			objectRefered.setSocio(socioService.findById(socioId));
			objectRefered.setMaterialBibliografico(principalService.findById(id));
			Calendar now = Calendar.getInstance();
			objectRefered.setFechaPrestamo(now.getTime());
			now.add(Calendar.DATE, 7);
			objectRefered.setFechaVencimiento(now.getTime());
			objectCreated = prestamoService.save(objectRefered);

		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", objectCreated );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);	
	}
	
	@PutMapping({"/{id}","/{id}/"})
	public ResponseEntity<?> update(@RequestBody MaterialBibliografico materialBibliografico, @PathVariable Long id) {
		MaterialBibliografico materialActual = principalService.findById(id);
		MaterialBibliografico materialUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if ( materialActual == null ) {
			return GlobalMessage.notFound();
		}
		try {			
			materialActual.setTitulo(materialBibliografico.getTitulo());
			materialActual.setCategoria(categoriaService.findById(materialBibliografico.getCategoria().getId()));
			materialActual.setFechaPublicacion(materialBibliografico.getFechaPublicacion());
			materialActual.setDescripcion(materialBibliografico.getDescripcion());
			materialUpdated = principalService.save(materialActual);
		} catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", materialUpdated);
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
	
	@PostMapping("/{id}/upload")
	public ResponseEntity<?> upload(@RequestParam("imgBiblio") MultipartFile archivo, @PathVariable("id") Long id ) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			DBFiles dbFile = uploadService.cargar(archivo);
			MaterialBibliografico mb = principalService.findById(id);
			
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/biblio/materialbibliografico/download/").path(dbFile.getId()).toUriString();
			response.put("data",fileDownloadUri);
			mb.setImgBiblio(fileDownloadUri);
			principalService.save(mb);
			
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
