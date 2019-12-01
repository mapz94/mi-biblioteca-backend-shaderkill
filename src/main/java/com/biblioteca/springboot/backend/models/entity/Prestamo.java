package com.biblioteca.springboot.backend.models.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* Clase Usuario, debe coincidir los atributos en el backend y frontend. 
 * Especificar si es una entidad y la tabla a la cual va asociada en minusculas y plural.*/
@Entity
@Table(name="prestamos")
public class Prestamo implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private Socio socio;

	@Column(nullable=false,name="material_bibliografico")
	private MaterialBibliografico materialBibliografico;

	@Column(nullable=false, name="fecha_prestamo")
	private Calendar fechaPrestamo;

	@Column(nullable=false, name="fecha_vencimiento")
	private Calendar fechaVencimiento;

	@Column(nullable=false, name="fecha_entrega")
	private Calendar fechaEntrega;

	@Column(nullable=true)
	private Prestamo prestamo;
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Socio getSocio() {
		return socio;
	}


	public void setSocio(Socio socio) {
		this.socio = socio;
	}


	public MaterialBibliografico getMaterialBibliografico() {
		return materialBibliografico;
	}


	public void setMaterialBibliografico(MaterialBibliografico materialBibliografico) {
		this.materialBibliografico = materialBibliografico;
	}


	public Calendar getFechaPrestamo() {
		return fechaPrestamo;
	}


	public void setFechaPrestamo(Calendar fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}


	public Calendar getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(Calendar fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	public Calendar getFechaEntrega() {
		return fechaEntrega;
	}


	public void setFechaEntrega(Calendar fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}


	public Prestamo getPrestamo() {
		return prestamo;
	}


	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}


	private static final long serialVersionUID = 1L;
}
