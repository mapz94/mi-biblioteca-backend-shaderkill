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
@Table(name="multas")
public class Multa implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private Prestamo prestamo;

	@Column(nullable=false)
	private long monto;

	@Column(nullable=false, name="estado_multa")
	private EstadoMulta estadoMulta;

	@Column(nullable=false, name="fecha_cancelacion")
	private Calendar fechaCancelacion;

	private static final long serialVersionUID = 1L;
}
