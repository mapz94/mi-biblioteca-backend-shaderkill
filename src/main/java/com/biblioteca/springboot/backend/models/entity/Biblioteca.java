package com.biblioteca.springboot.backend.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/* Clase Usuario, debe coincidir los atributos en el backend y frontend. 
 * Especificar si es una entidad y la tabla a la cual va asociada en minusculas y plural.*/
@Entity
@Table(name="bibliotecas")
public class Biblioteca implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=true)
	private String idMaterial;

	private String nombre;

	private String direccion;

	@Column(nullable=false)
	private Ciudad ciudad;

	@Column(nullable=false)
	private Pais pais;
	
	public Biblioteca() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(String idMaterial) {
		this.idMaterial = idMaterial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String dirección) {
		direccion = dirección;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	private static final long serialVersionUID = 1L;
}
