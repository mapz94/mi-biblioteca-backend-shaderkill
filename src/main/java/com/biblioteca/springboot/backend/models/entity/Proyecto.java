package com.biblioteca.springboot.backend.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "proyectos")
public class Proyecto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(nullable = false)
	private String autor;

	@OneToOne( optional = true, fetch = FetchType.LAZY, orphanRemoval= true)
    @JoinTable(name = "materialbibliografico_libro", 
      joinColumns = 
        { @JoinColumn(name = "entidad_id", referencedColumnName = "id") },
      inverseJoinColumns = 
        { @JoinColumn(name = "materialbibliografico_id", referencedColumnName = "id") })
	private MaterialBibliografico materialBibliografico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	static final long serialVersionUID = 1L;

	public MaterialBibliografico getMaterialBibliografico() {
		return materialBibliografico;
	}

	public void setMaterialBibliografico(MaterialBibliografico materialBibliografico) {
		this.materialBibliografico = materialBibliografico;
	}

}
