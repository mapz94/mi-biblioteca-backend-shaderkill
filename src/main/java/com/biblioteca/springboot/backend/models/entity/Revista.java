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
@Table(name="revistas")
public class Revista implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_revista;
	
	@Column(nullable = false)
	private String proveedor;
	
	@ManyToMany
	@JoinTable(name="materialbibliografico_revistas",joinColumns = 
			@JoinColumn(name="id_revista")
	, inverseJoinColumns =  @JoinColumn(name="id_materialbibliografico"))
	private List<MaterialBibliografico> materialesBibliograficos;
	
	
	
	public Long getId_revista() {
		return id_revista;
	}



	public void setId_revista(Long id_revista) {
		this.id_revista = id_revista;
	}



	public String getProveedor() {
		return proveedor;
	}



	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}



	public List<MaterialBibliografico> getMaterialesBibliograficos() {
		return materialesBibliograficos;
	}



	public void setMaterialesBibliograficos(List<MaterialBibliografico> materialesBibliograficos) {
		this.materialesBibliograficos = materialesBibliograficos;
	}



	private static final long serialVersionUID = 1L;
}
