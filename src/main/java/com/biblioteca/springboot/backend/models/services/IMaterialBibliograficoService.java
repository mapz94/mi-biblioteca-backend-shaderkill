package com.biblioteca.springboot.backend.models.services;

import java.util.List;
import com.biblioteca.springboot.backend.models.entity.MaterialBibliografico;

public interface IMaterialBibliograficoService {
	
	public List<MaterialBibliografico> findAll();
	
	public MaterialBibliografico findById(Long id);
	
	public MaterialBibliografico save(MaterialBibliografico material_bibliografico);
	
	public void delete(Long id);

}
