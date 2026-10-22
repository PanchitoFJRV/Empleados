package com.mx.empleados.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EMPLEADOS")
@Getter
@Setter
public class Empleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMPLEADO")
	private int id;
	
	@Column(name = "NSS")
	private String numeroSeguro;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "APELLIDO_PATERNO")
	private String apellidoPaterno;
	
	@Column(name = "APELLIDO_MATERNO")
	private String apellidoMaterno;
	
	@Column(name = "SUELDO")
	private double sueldo;
}
