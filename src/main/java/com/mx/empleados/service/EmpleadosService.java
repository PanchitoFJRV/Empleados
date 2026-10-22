package com.mx.empleados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.empleados.dao.EmpleadoRepository;
import com.mx.empleados.model.Empleado;

@Service
public class EmpleadosService {
	
	@Autowired
	private EmpleadoRepository repository;
	
	@Transactional(readOnly = true)
	public List<Empleado> mostrar(){
		return repository.findAll();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Empleado guardar(Empleado empleado) {
		if (repository.existsByNumeroSeguro(empleado.getNumeroSeguro())) {
	        return null;
	    }

	    return repository.save(empleado);
	}
	
	@Transactional(readOnly = true)
	public Empleado buscarPorId(int id) {
		return repository.findById(id)
				.orElse(null);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Empleado editar(int id, Empleado empleado) {
		Empleado encontrado = buscarPorId(id);
		
		if(encontrado == null) {
			return encontrado;
		}
		else {
			
			encontrado.setNumeroSeguro(empleado.getNumeroSeguro());
			encontrado.setNombre(empleado.getNombre());
			encontrado.setApellidoPaterno(empleado.getApellidoPaterno());
			encontrado.setApellidoMaterno(empleado.getApellidoMaterno());
			encontrado.setSueldo(empleado.getSueldo());
			
			return repository.save(encontrado) ;
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Empleado eliminar(int id) {
		
		Empleado encontrado = buscarPorId(id);
		
		if(encontrado == null) {
			return encontrado;
		}
		else {
			repository.delete(encontrado) ;
			return encontrado;
		}
		
	}

}
