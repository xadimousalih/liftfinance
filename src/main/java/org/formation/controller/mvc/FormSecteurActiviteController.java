package org.formation.controller.mvc;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.formation.model.SecteurActivite;
import org.formation.repositories.SecteurActiviteRepository;
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
public class FormSecteurActiviteController {

	@Autowired
	private SecteurActiviteRepository secteurActiviteRepository;

	@GetMapping(value = "form-secteurs-activites")
	public ModelMap mmFormElements(Model model) {
		model.addAttribute("secteuractivite", new SecteurActivite());
		return new ModelMap();
	}

	@ModelAttribute("secteursactivites")
	public List<SecteurActivite> messages() {
		return secteurActiviteRepository.findAll();
	}

	@RequestMapping(value = "find-secteurs-activites")
	public String findSecteurActivites(Model model) {

		model.addAttribute("secteursactivites", secteurActiviteRepository.findAll());
		return "secteursactivites";
	}

	@GetMapping("/edit-secteur-activite")
	public String editSecteurActivite(@RequestParam("secteuractiviteId") long secteuractiviteId, Model model) {
		model.addAttribute("secteuractivite",
				secteurActiviteRepository.findById(secteuractiviteId).orElseThrow(() -> new EntityNotFoundException()));
		return "/admin/form-secteur-activite";
	}

	@PostMapping(path = "/save-secteur-activite", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String save(@Valid SecteurActivite secteuractivite, Model model, BindingResult result) {

		SecteurActivite existingSecteurActivite = secteurActiviteRepository.findByCode(secteuractivite.getCode());

		if (existingSecteurActivite != null && existingSecteurActivite.getCode() != null
				&& !existingSecteurActivite.getCode().isEmpty()) {

			existingSecteurActivite.setCode(secteuractivite.getCode());
			existingSecteurActivite.setName(secteuractivite.getName());
			existingSecteurActivite.setTypeEntreprise(secteuractivite.getTypeEntreprise());

			secteuractivite = secteurActiviteRepository.save(existingSecteurActivite);

			result.rejectValue("code", null,
					"There is already a secteur activite saved with the same reference. Update Done");

		}

		if (result.hasErrors()) {
			model.addAttribute("secteuractivite", secteuractivite);
			return "/admin/form-secteursactivites";
		}
		secteuractivite = secteurActiviteRepository.save(secteuractivite);

		model.addAttribute("secteursactivites", secteurActiviteRepository.findAll());
		return "redirect:/admin/form-secteursactivites?success";

	}

	@GetMapping("/delete-secteur-activite")
	public String deleteSecteurActivite(@RequestParam("secteuractiviteId") long secteuractiviteId, Model model) {
		SecteurActivite saToDelete = secteurActiviteRepository.findById(secteuractiviteId).get();
		secteurActiviteRepository.delete(saToDelete);
		model.addAttribute("secteuractivite", new SecteurActivite());
		model.addAttribute("secteursactivites", secteurActiviteRepository.findAll());


		return "/admin/form-secteurs-activites";
	}

}
