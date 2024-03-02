package org.formation.controller.rest;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.formation.model.Categorie;
import org.formation.repositories.CategorieRepository;
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
@RequestMapping("/api/categories")
public class CategorieRestController {

	@Autowired
	CategorieRepository categorieRepository;
	
	@GetMapping("{name}")
	public Categorie findByName(@PathVariable("categorie") String name) {
		return categorieRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Categorie inconnu :"+name));
	}
	
	@PostMapping
	ResponseEntity<Categorie> createCategorie(@Valid @RequestBody Categorie categorie) {
		
		categorie = categorieRepository.save(categorie);
		
		return new ResponseEntity<Categorie>(categorie,HttpStatus.CREATED);
		
	}

}
