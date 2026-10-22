package com.mx.empleados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.empleados.dao.EmpleadoRepository;

@Service
public class EmpleadosService {
	
	@Autowired
	private EmpleadoRepository repository;
	
	

}
