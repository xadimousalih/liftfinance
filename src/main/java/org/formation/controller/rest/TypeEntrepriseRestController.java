package org.formation.controller.rest;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.formation.model.TypeEntreprise;
import org.formation.repositories.TypeEntrepriseRepository;
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
@RequestMapping("/api/typeentreprises")
public class TypeEntrepriseRestController {

	@Autowired
	TypeEntrepriseRepository typeEntrepriseRepository;
	
	@GetMapping("{reference}")
	public TypeEntreprise findByName(@PathVariable("categorie") String name) {
		return typeEntrepriseRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Type d'ntreprise inconnu :"+name));
	}
	
	@PostMapping
	ResponseEntity<TypeEntreprise> createTypeEntreprise(@Valid @RequestBody TypeEntreprise typeEntreprise) {
		
		typeEntreprise = typeEntrepriseRepository.save(typeEntreprise);
		
		return new ResponseEntity<TypeEntreprise>(typeEntreprise,HttpStatus.CREATED);
		
	}
}
