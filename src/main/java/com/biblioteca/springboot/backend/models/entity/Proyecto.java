package com.biblioteca.springboot.backend.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="proyectos")
public class Proyecto implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_proyecto;

	@Column(nullable = false)
	private String autor;
	
	@OneToMany
	@JoinTable(name="materialbibliografico_proyectos",joinColumns = 
			@JoinColumn(name="id_proyecto")
	, inverseJoinColumns =  @JoinColumn(name="id_materialbibliografico"))
	private MaterialBibliografico materialesBibliograficos;

	
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


	public MaterialBibliografico getMaterialesBibliograficos() {
		return materialesBibliograficos;
	}


	public void setMaterialesBibliograficos(MaterialBibliografico materialesBibliograficos) {
		this.materialesBibliograficos = materialesBibliograficos;
	}


	static final long serialVersionUID = 1L;
}
