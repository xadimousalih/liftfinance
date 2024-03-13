package org.formation.controller.rest;

import javax.validation.Valid;

import org.formation.model.Ecriture;
import org.formation.model.MoisEnum;
import org.formation.repositories.EcritureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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


	@GetMapping("/list")
	public Page<Ecriture> getAllEcritures(
			@RequestParam(name = "mois", required = false) MoisEnum mois,
			@RequestParam(name = "categorieRef", required = false) String categorieRef,
			Pageable pageable) {
		if (mois != null && categorieRef != null) {
			return ecritureRepository.findByMoisAndCategorie_Reference(mois, categorieRef, pageable);
		} else if (mois != null) {
			return ecritureRepository.findByMois(mois, pageable);
		} else if (categorieRef != null) {
			return ecritureRepository.findByCategorie_Reference(categorieRef, pageable);
		} else {
			return ecritureRepository.findAll(pageable);
		}
	}


}
