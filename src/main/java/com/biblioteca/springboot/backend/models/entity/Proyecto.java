package com.biblioteca.springboot.backend.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="proyectos")
public class Proyecto implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_proyecto;

	@Column(nullable = false)
	private String autor;
	
	@ManyToMany
	@JoinTable(name="materialbibliografico_proyectos",joinColumns = 
			@JoinColumn(name="id_proyecto")
	, inverseJoinColumns =  @JoinColumn(name="id_materialbibliografico"))
	private List<MaterialBibliografico> materialesBibliograficos;

	
	 public Long getId_proyecto() {
		return id_proyecto;
	}


	public void setId_proyecto(Long id_proyecto) {
		this.id_proyecto = id_proyecto;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public List<MaterialBibliografico> getMaterialesBibliograficos() {
		return materialesBibliograficos;
	}


	public void setMaterialesBibliograficos(List<MaterialBibliografico> materialesBibliograficos) {
		this.materialesBibliograficos = materialesBibliograficos;
	}


	static final long serialVersionUID = 1L;
}
