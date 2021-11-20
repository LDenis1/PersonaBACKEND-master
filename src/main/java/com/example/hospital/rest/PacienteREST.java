package com.example.hospital.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.model.Paciente;
import com.example.hospital.service.PacienteService;

@RestController
@RequestMapping("/paciente/")
public class PacienteREST {
	
	
	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping
	private ResponseEntity<List<Paciente>> getAllPacientes (){
		return ResponseEntity.ok(pacienteService.findAll());
	}
	
	@PostMapping
	private ResponseEntity<Paciente> savePaciente (@RequestBody Paciente paciente){
		try {
			Paciente pacienteGuardada = pacienteService.save(paciente);		
		return ResponseEntity.created(new URI("/paciente/"+pacienteGuardada.getId())).body(pacienteGuardada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping (value = "delete/{id}")
	private ResponseEntity<Boolean> deletePaciente (@PathVariable ("id") Long id){
		pacienteService.deleteById(id);
		return ResponseEntity.ok(!(pacienteService.findById(id)!=null));
		
	}


}
