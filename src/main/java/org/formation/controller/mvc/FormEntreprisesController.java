package org.formation.controller.mvc;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.formation.model.Entreprise;
import org.formation.model.SecteurActivite;
import org.formation.model.TypeEntreprise;
import org.formation.repositories.EntrepriseRepository;
import org.formation.repositories.SecteurActiviteRepository;
import org.formation.repositories.TypeEntrepriseRepository;
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
@RequestMapping(value = "admin")
public class FormEntreprisesController {
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private TypeEntrepriseRepository typeEntrepriseRepository;
	@Autowired
	private SecteurActiviteRepository secteurActiviteRepository;

	@GetMapping(value = "form-entreprises")
	public ModelMap mmFormElements(Model model) {
		model.addAttribute("entreprise", new Entreprise());

//		model.addAttribute("typesentreprises", typeEntrepriseRepository.findAll());
		model.addAttribute("secteursactivites", secteurActiviteRepository.findAll());

		return new ModelMap();
	}

	@ModelAttribute("entreprises")
	public List<Entreprise> getEntreprises() {
		return entrepriseRepository.findAll();
	}
	
	@ModelAttribute("typesentreprises")
	public List<TypeEntreprise> getTypesEntreprises() {
		return typeEntrepriseRepository.findAll();
	}
	
	@ModelAttribute("secteursactivites")
	public List<SecteurActivite> getSecteursActivites() {
		return secteurActiviteRepository.findAll();
	}


	@RequestMapping(value = "find-entreprises")
	public String findEntreprises(Model model) {

		//model.addAttribute("entreprises", entrepriseRepository.findAll());
		return "entreprises";
	}

	@GetMapping("/edit-entreprise")
	public String editEntreprise(@RequestParam("entrepriseId") long entrepriseId, Model model) {
		model.addAttribute("entreprise",
				entrepriseRepository.findById(entrepriseId).orElseThrow(() -> new EntityNotFoundException()));
		//model.addAttribute("secteursactivites", secteurActiviteRepository.findAll());

		return "/admin/form-edit-entreprise";
	}

	@PostMapping(path = "/save-entreprise", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String save(@Valid Entreprise entreprise, Model model, BindingResult result) {

		Entreprise existingEntreprise = entrepriseRepository.findByCode(entreprise.getCode());

		if (existingEntreprise != null && existingEntreprise.getCode() != null
				&& !existingEntreprise.getCode().isEmpty()) {

			existingEntreprise.setCode(entreprise.getCode());
			existingEntreprise.setName(entreprise.getName());
			existingEntreprise.setAdresse(entreprise.getAdresse());
			existingEntreprise.setRegion(entreprise.getRegion());
			existingEntreprise.setPays(entreprise.getPays());
			existingEntreprise.setEmail(entreprise.getEmail());
			existingEntreprise.setTelephone(entreprise.getTelephone());
			existingEntreprise.setChiffreAffaire(entreprise.getChiffreAffaire());
			existingEntreprise.setCreated(entreprise.getCreated());
			existingEntreprise.setSecteurActivite(entreprise.getSecteurActivite());

			entreprise = entrepriseRepository.save(existingEntreprise);

			result.rejectValue("code", null,
					"There is already a entreprise saved with the same code. Update Done");

		}

		if (result.hasErrors()) {
			model.addAttribute("entreprise", entreprise);
			return "/admin/form-entreprises";
		}
		entreprise = entrepriseRepository.save(entreprise);

		model.addAttribute("entreprises", entrepriseRepository.findAll());
		return "redirect:/admin/form-entreprises?success";

	}

	@GetMapping("/delete-entreprise")
	public String deleteEntreprise(@RequestParam("entrepriseId") long entrepriseId, Model model) {
		Entreprise catToDelete = entrepriseRepository.findById(entrepriseId).get();
		entrepriseRepository.delete(catToDelete);
		model.addAttribute("entreprise", new Entreprise());
//		model.addAttribute("entreprises", entrepriseRepository.findAll());

		return "/admin/form-entreprises";
	}

}
