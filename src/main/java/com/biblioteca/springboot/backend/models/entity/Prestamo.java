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

	@Column(nullable=false)
	private Prestamo prestamo;

	private static final long serialVersionUID = 1L;
}
