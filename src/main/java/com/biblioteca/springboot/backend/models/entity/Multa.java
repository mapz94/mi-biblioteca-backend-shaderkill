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
@Table(name="multas")
public class Multa implements Serializable { 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;

	
	@OneToOne
	@JoinColumn(name="prestamo_id")
	//@Column(nullable=false)
	private Prestamo prestamo;

	@Column(nullable=false)
	private long monto;

	/*@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="estado_multa", referencedColumnName = "id", 
	unique= true, nullable=true, insertable=true, updatable=true)*/
	
	@OneToOne
	@JoinColumn(name="estadoMulta_id")
	//@Column(nullable = false)
	private EstadoMulta estadoMulta;

	@Column(nullable=true, name="fecha_cancelacion")
	private Date fechaCancelacion;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public long getMonto() {
		return monto;
	}

	public void setMonto(long monto) {
		this.monto = monto;
	}

	public EstadoMulta getEstadoMulta() {
		return estadoMulta;
	}

	public void setEstadoMulta(EstadoMulta estadoMulta) {
		this.estadoMulta = estadoMulta;
	}

	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}


	private static final long serialVersionUID = 1L;
}
