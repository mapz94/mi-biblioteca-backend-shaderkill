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
@Table(name="libros")
public class Libro implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_libro;

	@Column(nullable = false, unique = true)
	private String isbn;

	@Column(nullable = false, unique = true)
	private String editorial;

	@Column(nullable = false, unique = true)
	private String autor;
	
	@ManyToMany
	@JoinTable(name="materialbibliografico_libros",joinColumns = 
			@JoinColumn(name="id_libro")
	, inverseJoinColumns =  @JoinColumn(name="id_materialbibliografico"))
	private List<MaterialBibliografico> materialesBibliograficos;
	
	public Long getId_libro() {
		return id_libro;
	}

	public void setId_libro(Long id_libro) {
		this.id_libro = id_libro;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
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

	private static final long serialVersionUID = 1L;
}
