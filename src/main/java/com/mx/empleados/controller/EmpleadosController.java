package com.mx.empleados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.empleados.model.Empleado;
import com.mx.empleados.service.EmpleadosService;

@RestController
@RequestMapping("api/vi/empleados")
@CrossOrigin
public class EmpleadosController {
	
	@Autowired
	private EmpleadosService service;
	
	@GetMapping
	public ResponseEntity<?> mostrar(){
		List<Empleado> empleados = service.mostrar();
		
		if (empleados.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("No hay empleados registrados");
	    }

	    return ResponseEntity.ok(empleados);
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Empleado empleado){
		try {
			Empleado empleadoGuardado = service.guardar(empleado);
			
			if(empleadoGuardado != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(empleadoGuardado);
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El NSS: " 
			+ empleado.getNumeroSeguro() + " ya existe");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar: "
					+ e.getMessage());
		}
		
	}
	
	@PostMapping(path = "/{id_empleado}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id_empleado") int id){
		Empleado empleado = service.buscarPorId(id);
		
		if (empleado == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("No se encontro el empleado con el id: " + id);
	    }

	    return ResponseEntity.ok(empleado);
	}
	
	@PutMapping(path = "{id_empleado}")
	public ResponseEntity<?> editar(@PathVariable("id_empleado")int id, @RequestBody Empleado empleado){
		try {
			Empleado editado = service.editar(id, empleado);
			
			if (editado == null) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                .body("No se encontro el empleado con el id: " + id);
		    }

		    return ResponseEntity.ok(editado);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar: "
					+ e.getMessage());
		}
	}
	
	@DeleteMapping(path = "{id_empleado}")
	public ResponseEntity<?>eliminar(@PathVariable("id_empleado")int id){
		Empleado eliminado = service.eliminar(id);
		
		if (eliminado == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("No se encontro el empleado con el id: " + id);
	    }

	    return ResponseEntity.ok(eliminado);
	}
	

}