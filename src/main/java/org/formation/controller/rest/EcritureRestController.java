package org.formation.controller.rest;

import javax.validation.Valid;

import org.formation.model.Ecriture;
import org.formation.repositories.EcritureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ecritures")
public class EcritureRestController {

	@Autowired
	private EcritureRepository ecritureRepository;
	
	@GetMapping("{libelle}")
	public Ecriture findByLibelle(@PathVariable("ecriture") String numero) {
		return ecritureRepository.findByNumero(numero);
	}
	
	@PostMapping
	ResponseEntity<Ecriture> createEcriture(@Valid @RequestBody Ecriture ecriture) {
		
		ecriture = ecritureRepository.save(ecriture);
		
		return new ResponseEntity<Ecriture>(ecriture, HttpStatus.CREATED);
		
	}
}
