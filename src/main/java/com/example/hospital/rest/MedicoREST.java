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

import com.example.hospital.model.Medico;
import com.example.hospital.service.MedicoService;

@RestController
@RequestMapping("/medico/")
public class MedicoREST {
	
	
	@Autowired
	private MedicoService medicoService;
	
	@GetMapping
	private ResponseEntity<List<Medico>> getAllMedicos (){
		return ResponseEntity.ok(medicoService.findAll());
	}
	
	@PostMapping
	private ResponseEntity<Medico> saveMedico (@RequestBody Medico medico){
		try {
			Medico medicoGuardada = medicoService.save(medico);		
		return ResponseEntity.created(new URI("/medico/"+medicoGuardada.getId())).body(medicoGuardada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping (value = "delete/{id}")
	private ResponseEntity<Boolean> deleteMedico (@PathVariable ("id") Long id){
		medicoService.deleteById(id);
		return ResponseEntity.ok(!(medicoService.findById(id)!=null));
		
	}

	
	
}
