package com.biblioteca.springboot.backend.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/* Clase Usuario, debe coincidir los atributos en el backend y frontend. 
 * Especificar si es una entidad y la tabla a la cual va asociada en minusculas y plural.*/
@Entity
@Table(name="socios")
public class Socio implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	private String password;

	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(name="img_avatar")
	private String imgAvatar;

	@Column(nullable=false)
	private Biblioteca biblioteca;

	@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="Persona_id", referencedColumnName = "id", unique= true, nullable=true, insertable=true, updatable=true)
	private Persona persona;
	
	public Socio() {}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImgAvatar() {
		return imgAvatar;
	}

	public void setImgAvatar(String imgAvatar) {
		this.imgAvatar = imgAvatar;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private static final long serialVersionUID = 1L;
}
