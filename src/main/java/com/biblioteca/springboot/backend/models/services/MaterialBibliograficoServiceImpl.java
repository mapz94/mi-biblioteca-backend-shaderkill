package com.biblioteca.springboot.backend.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.springboot.backend.models.dao.IMaterialBibliograficoDao;
import com.biblioteca.springboot.backend.models.entity.MaterialBibliografico;


@Service
public class MaterialBibliograficoServiceImpl implements IMaterialBibliograficoService {
	
	@Autowired
	private IMaterialBibliograficoDao material_bibliografico_Dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<MaterialBibliografico> findAll() {
		
		return (List<MaterialBibliografico>) material_bibliografico_Dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public MaterialBibliografico findById(Long id) {
		
		return material_bibliografico_Dao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public MaterialBibliografico save(MaterialBibliografico material_bibliografico) {
		
		return material_bibliografico_Dao.save(material_bibliografico);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		material_bibliografico_Dao.deleteById(id);
	}
	
}
