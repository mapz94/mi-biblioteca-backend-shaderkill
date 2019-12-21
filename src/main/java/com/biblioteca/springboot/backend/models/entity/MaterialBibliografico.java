package com.biblioteca.springboot.backend.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "materialbibliografico")
public class MaterialBibliografico implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;

	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(nullable = true)
	private Categoria categoria;

	@Column(nullable = true, name = "fecha_publicacion")
	private Date fechaPublicacion;
	
	@JsonIgnoreProperties("materialBibliografico")
	@OneToOne(mappedBy = "materialBibliografico",cascade = CascadeType.PERSIST)
	private Libro libro;
	
	@JsonIgnoreProperties("materialBibliografico")
	@OneToOne(mappedBy = "materialBibliografico",cascade = CascadeType.PERSIST)
	private Revista revista;
	
	@JsonIgnoreProperties("materialBibliografico")
	@OneToOne(mappedBy = "materialBibliografico",cascade = CascadeType.PERSIST)
	private Proyecto proyecto;
	

	@Column(name = "img_biblio", nullable = true)
	private String imgBiblio;
	
	@Lob
	@Column(name = "descripcion", nullable = true)
	private String descripcion;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		
	}
	
	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Revista getRevista() {
		return revista;
	}

	public void setRevista(Revista revista) {
		this.revista = revista;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	private static final long serialVersionUID = 1L;
}
