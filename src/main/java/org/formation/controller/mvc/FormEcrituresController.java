package org.formation.controller.mvc;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.formation.model.Categorie;
import org.formation.model.Ecriture;
import org.formation.model.Entreprise;
import org.formation.model.LibelleEcriture;
import org.formation.repositories.CategorieRepository;
import org.formation.repositories.EcritureRepository;
import org.formation.repositories.EntrepriseRepository;
import org.formation.repositories.LibelleEcritureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "pages")
public class FormEcrituresController {
	@Autowired
	private EcritureRepository ecritureRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private LibelleEcritureRepository libelleEcritureRepository;


	@GetMapping(value = "form-ecritures")
	public ModelMap mmFormElements(Model model) {
		model.addAttribute("ecriture", new Ecriture());
		return new ModelMap();
	}



	@ModelAttribute("libelles")
	public List<LibelleEcriture> getLibellesEcriture() {return libelleEcritureRepository.findAll();
	}

	@ModelAttribute("ecritures")
	public List<Ecriture> getEcritures() {
		return ecritureRepository.findAll();
	}
	
	@ModelAttribute("entreprises")
	public List<Entreprise> getEntreprises() {
		return entrepriseRepository.findAll();
	}
	
	@ModelAttribute("categories")
	public List<Categorie> getCategories() {
		return categorieRepository.findAll();
	}

	@RequestMapping(value = "find-ecritures")
	public String findEcritures(Model model) {
		return "ecritures";
	}

	@GetMapping("/edit-ecriture")
	public String editEcriture(@RequestParam("ecritureId") long ecritureId, Model model) {
		model.addAttribute("ecriture",
				ecritureRepository.findById(ecritureId).orElseThrow(() -> new EntityNotFoundException()));
		return "/pages/form-edit-ecriture";
	}

	@PostMapping(path = "/save-ecriture", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String save(@Valid Ecriture ecriture, Model model, BindingResult result) {
		
		

		Ecriture existingEcriture = ecritureRepository.findByNumero(ecriture.getNumero());

		if (existingEcriture != null && existingEcriture.getNumero() != null
				&& !existingEcriture.getNumero().isEmpty()) {

			existingEcriture.setNumero(ecriture.getNumero());
			existingEcriture.setLibelle(ecriture.getLibelle());
			existingEcriture.setCreated(ecriture.getCreated());
			existingEcriture.setDisabled(ecriture.getDisabled());

			existingEcriture.setMontantEcriture(ecriture.getMontantEcriture());

			existingEcriture.setTotalMensuel(ecriture.getTotalMensuel());
			existingEcriture.setTotalAnnee(ecriture.getTotalAnnee());
			existingEcriture.setTotalGlobal(ecriture.getTotalGlobal());
			
			existingEcriture.setMois(ecriture.getMois());
			
			existingEcriture.setCategorie(ecriture.getCategorie());
			existingEcriture.setEntreprise(entrepriseRepository.findByCode("TAB28"));
			
			ecriture = ecritureRepository.save(existingEcriture);
			result.rejectValue("numero", null,
					"There is already a ecriture saved with the same numero. Update Done");

		}

		if (result.hasErrors()) {
			model.addAttribute("ecriture", ecriture);
			return "/pages/form-ecritures";
		}
		ecriture.setEntreprise(entrepriseRepository.findByCode("TAB28"));
		ecriture = ecritureRepository.save(ecriture);
		model.addAttribute("ecritures", ecritureRepository.findAll());
		return "redirect:/pages/form-ecritures?success";

	}

	@GetMapping("/delete-ecriture")
	public String deleteEcriture(@RequestParam("ecritureId") long ecritureId, Model model) {
		Ecriture catToDelete = ecritureRepository.findById(ecritureId).get();
		ecritureRepository.delete(catToDelete);
		model.addAttribute("ecriture", new Ecriture());
		return "/pages/form-ecritures";
	}

}
