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

	@Column(nullable=true)
	private Categoria categoria;

	@Column(nullable=true, name = "fecha_publicacion")
	private Date fechaPublicacion;
	
	@Column(name="img_biblio",nullable=true)
	private String imgBiblio;
	
	@Column(name = "mat_libro",nullable=true)
	private Libro libro;
	
	@Column(name = "mat_revista",nullable=true)
	private Revista revista;
	
	@Column(name = "mat_proyecto",nullable=true)
	private Proyecto proyecto;

	public Long getId() {
		return id;
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
	
	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public void setRevista(Revista revista) {
		this.revista = revista;
	}
	public Revista getRevista() {
		return revista;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	public Proyecto getProyecto() {
		return proyecto;
	}

	public String getImgBiblio() {
		return imgBiblio;
	}

	public void setImgBiblio(String imgBiblio) {
		this.imgBiblio = imgBiblio;
	}

	private static final long serialVersionUID = 1L;
	
}
