package com.biblioteca.springboot.backend.models.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="materialbibliografico")
public class MaterialBibliografico implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_materialbibliografico")
	private Long id;
	
	@Column(nullable = false)
	private String titulo;


	@Column(nullable=true)
	private Categoria categoria;

	@Column(nullable=true, name = "fecha_publicacion")
	private Date fechaPublicacion;
	
	@Column(name="img_biblio",nullable=true)
	private String imgBiblio;

	public Long getId() {
		return id;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getImgBiblio() {
		return imgBiblio;
	}

	public void setImgBiblio(String imgBiblio) {
		this.imgBiblio = imgBiblio;
	}

	private static final long serialVersionUID = 1L;
	
}
