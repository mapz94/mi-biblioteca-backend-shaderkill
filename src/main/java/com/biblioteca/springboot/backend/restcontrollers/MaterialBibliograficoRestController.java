package com.biblioteca.springboot.backend.restcontrollers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.biblioteca.springboot.backend.GlobalMessage;
import com.biblioteca.springboot.backend.models.entity.MaterialBibliografico;
import com.biblioteca.springboot.backend.models.services.IUploadFileService;
import com.biblioteca.springboot.backend.models.services.IMaterialBibliograficoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
// @CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/biblio/materialbibliografico")
public class MaterialBibliograficoRestController {
	
	@Autowired
	private IMaterialBibliograficoService principalService;
	
	@Autowired
	private IUploadFileService uploadService;
	
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
			response.put("mensaje", "Error al realizar la busqueda en la base de datos.");
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
			objectCreated = principalService.save(objectRefered);
		} catch(DataAccessException e) {
			return GlobalMessage.internalServerError();
		}
		//response.put("mensaje", "El material bibliografico ha sido creado con éxito!.");
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
			materialActual.setCategoria(materialBibliografico.getCategoria());
			materialActual.setFechaPublicacion(materialBibliografico.getFechaPublicacion());
			materialUpdated = principalService.save(materialActual);
		} catch(DataAccessException e) {
			return GlobalMessage.internalServerError();
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
	
	@PostMapping("/materialBibliografico/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id ) {
		Map<String, Object> response = new HashMap<>();
		
		MaterialBibliografico mb = principalService.findById(id);
		
		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				return GlobalMessage.internalServerError();
			}
			
			String avatarAnterior = mb.getImgBiblio();
			uploadService.eliminar(avatarAnterior);
			mb.setImgBiblio(nombreArchivo);
			principalService.save(mb);
			
			response.put("Material Bibliografico", mb);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreBiblio:.+}")
	public ResponseEntity<Resource> verAvatar(@PathVariable String nombreBiblio) {
		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreBiblio);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
}
