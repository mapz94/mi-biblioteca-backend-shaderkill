package com.biblioteca.springboot.backend.models.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;

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
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Libro libro;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Revista revista;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Proyecto proyecto;
	
	public Libro getLibros() {
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
