package com.biblioteca.springboot.backend.models.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="prestamos")
public class Prestamo implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=true)
	private Socio socio;

	@Column(nullable=true,name="material_bibliografico")
	private MaterialBibliografico materialBibliografico;

	@Column(nullable=true, name="fecha_prestamo")
	private Date fechaPrestamo;

	@Column(nullable=true, name="fecha_vencimiento")
	private Date fechaVencimiento;

	@Column(nullable=true, name="fecha_entrega")
	private Date fechaEntrega;
	
	@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="estado_multa", referencedColumnName = "id", unique= true, nullable=true, insertable=true, updatable=true)
	private EstadoPrestamo estadoPrestamo;
	

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


	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}


	public void setFechaPrestamo(Date fecha) {
		this.fechaPrestamo = fecha;
	}


	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	public Date getFechaEntrega() {
		return fechaEntrega;
	}


	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	public EstadoPrestamo getEstadoPrestamo() {
		return estadoPrestamo;
	}


	public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}


	public Prestamo getPrestamo() {
		return prestamo;
	}


	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}


	private static final long serialVersionUID = 1L;
}
