package com.biblioteca.springboot.backend.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "materialbibliografico")
public class MaterialBibliografico implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_materialbibliografico")
	private Long id;
	
	@Column(name = "titutlo", nullable = false)
	private String titulo;

	@Column(nullable = true)
	private Categoria categoria;

	@Column(nullable = true, name = "fecha_publicacion")
	private Date fechaPublicacion;

	@Column(name = "img_biblio", nullable = true)
	private String imgBiblio;

	@OneToMany(mappedBy = "materialBibliografico")
	private List<Libro> libros = new ArrayList<Libro>();

	@OneToMany(mappedBy = "materialBibliografico")
	private List<Revista> revistas = new ArrayList<Revista>();

	@OneToMany(mappedBy = "materialBibliografico")
	private List<Proyecto> proyectos = new ArrayList<Proyecto>();

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

	private static final long serialVersionUID = 1L;

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public List<Revista> getRevistas() {
		return revistas;
	}

	public void setRevistas(List<Revista> revistas) {
		this.revistas = revistas;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

}
