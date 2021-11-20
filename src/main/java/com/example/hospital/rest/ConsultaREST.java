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

import com.example.hospital.model.Consulta;
import com.example.hospital.service.ConsultaService;

@RestController
@RequestMapping("/consulta/")
public class ConsultaREST {
	
	@Autowired
	private ConsultaService personaService;
	
	@GetMapping
	private ResponseEntity<List<Consulta>> getAllPersonas (){
		return ResponseEntity.ok(personaService.findAll());
	}
	
	@PostMapping
	private ResponseEntity<Consulta> savePersona (@RequestBody Consulta persona){
		try {
			Consulta personaGuardada = personaService.save(persona);		
		return ResponseEntity.created(new URI("/personas/"+personaGuardada.getId())).body(personaGuardada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping (value = "delete/{id}")
	private ResponseEntity<Boolean> deletePersona (@PathVariable ("id") Long id){
		personaService.deleteById(id);
		return ResponseEntity.ok(!(personaService.findById(id)!=null));
		
	}

}
