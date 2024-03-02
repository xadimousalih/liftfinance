package org.formation.controller.rest;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.formation.model.Entreprise;
import org.formation.repositories.EntrepriseRepository;
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
@RequestMapping("/api/entreprises")
public class EntrepriseRestController {

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@GetMapping("{name}")
	public Entreprise findByName(@PathVariable("entreprise") String name) {
		return entrepriseRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Entreprise inconnue :"+name));
	}
	
	@PostMapping
	ResponseEntity<Entreprise> createEntreprise(@Valid @RequestBody Entreprise entreprise) {
		
		entreprise = entrepriseRepository.save(entreprise);
		
		return new ResponseEntity<Entreprise>(entreprise, HttpStatus.CREATED);
		
	}
}
