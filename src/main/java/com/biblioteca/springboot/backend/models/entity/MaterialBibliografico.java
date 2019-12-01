package com.biblioteca.springboot.backend.models.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="materialbibliografico")
public class MaterialBibliografico implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_materialbibliografico")
	private Long id;

	@Column(nullable=false)
	private String titulo;

	@Column(nullable=true)
	private Categoria categoria;

	@Column(nullable=true, name = "fecha_publicacion")
	private Calendar fechaPublicacion = Calendar.getInstance();
	
	@Column(name="img_biblio")
	private String imgBiblio;
	
	@ManyToMany(mappedBy = "materialesBibliograficos")
	private List<Libro> libros;
	
	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public String getImgBiblio() {
		return imgBiblio;
	}

	public void setImgBiblio(String imgBiblio) {
		this.imgBiblio = imgBiblio;
	}

	private static final long serialVersionUID = 1L;

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

	public Calendar getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Calendar fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	
}
