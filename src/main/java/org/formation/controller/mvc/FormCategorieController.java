package org.formation.controller.mvc;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.formation.model.Categorie;
import org.formation.repositories.CategorieRepository;
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
public class FormCategorieController {

	@Autowired
	private CategorieRepository categorieRepository;

	@GetMapping(value = "form-categories")
	public ModelMap mmFormElements(Model model) {
		model.addAttribute("categorie", new Categorie());
		return new ModelMap();
	}

	@ModelAttribute("categories")
	public List<Categorie> getCategories() {
		return categorieRepository.findAll();
	}

	@RequestMapping(value = "find-categories")
	public String findCategories(Model model) {

		model.addAttribute("categories", categorieRepository.findAll());
		return "categories";
	}

	@GetMapping("/edit-categorie")
	public String editCategorie(@RequestParam("categorieId") long categorieId, Model model) {
		model.addAttribute("categorie",
				categorieRepository.findById(categorieId).orElseThrow(() -> new EntityNotFoundException()));
		return "/admin/form-edit-categorie";
	}

	@PostMapping(path = "/save-categorie", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String save(@Valid Categorie categorie, Model model, BindingResult result) {

		Categorie existingCategorie = categorieRepository.findByReference(categorie.getReference());

		if (existingCategorie != null && existingCategorie.getReference() != null
				&& !existingCategorie.getReference().isEmpty()) {

			existingCategorie.setReference(categorie.getReference());
			existingCategorie.setName(categorie.getName());
			existingCategorie.setDescription(categorie.getDescription());

			categorie = categorieRepository.save(existingCategorie);

			result.rejectValue("reference", null,
					"There is already a categorie saved with the same reference. Update Done");

		}

		if (result.hasErrors()) {
			model.addAttribute("categorie", categorie);
			return "/admin/form-categories";
		}
		categorie = categorieRepository.save(categorie);

		model.addAttribute("categories", categorieRepository.findAll());
		return "redirect:/admin/form-categories?success";

	}

	@GetMapping("/delete-categorie")
	public String deleteCategorie(@RequestParam("categorieId") long categorieId, Model model) {
		Categorie catToDelete = categorieRepository.findById(categorieId).get();
		categorieRepository.delete(catToDelete);
		model.addAttribute("categorie", new Categorie());
		model.addAttribute("categories", categorieRepository.findAll());


		return "/admin/form-categories";
	}

}
