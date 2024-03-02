package org.formation.controller.mvc;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.formation.model.TypeEntreprise;
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
public class FormTypeEntrepriseController {

	@Autowired
	private TypeEntrepriseRepository typeEntrepriseRepository;

	@GetMapping(value = "form-types-entreprises")
	public ModelMap mmFormElements(Model model) {
		model.addAttribute("typeentreprise", new TypeEntreprise());
		return new ModelMap();
	}

	@ModelAttribute("typesentreprises")
	public List<TypeEntreprise> messages() {
		return typeEntrepriseRepository.findAll();
	}

	@RequestMapping(value = "find-types-entreprises")
	public String findTypeEntreprises(Model model) {

		model.addAttribute("typesentreprises", typeEntrepriseRepository.findAll());
		return "typesentreprises";
	}

	@GetMapping("/edit-types-entreprises")
	public String editTypeEntreprise(@RequestParam("typeentrepriseId") long typeentrepriseId, Model model) {
		model.addAttribute("typeentreprise",
				typeEntrepriseRepository.findById(typeentrepriseId).orElseThrow(() -> new EntityNotFoundException()));
		return "/admin/form-edit-type-entreprise";
	}

	@PostMapping(path = "/save-types-entreprises", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String save(@Valid TypeEntreprise typeentreprise, Model model, BindingResult result) {

		TypeEntreprise existingTypeEntreprise = typeEntrepriseRepository.findByCode(typeentreprise.getCode());

		if (existingTypeEntreprise != null && existingTypeEntreprise.getCode() != null
				&& !existingTypeEntreprise.getCode().isEmpty()) {
			existingTypeEntreprise.setCode(typeentreprise.getCode());
			existingTypeEntreprise.setName(typeentreprise.getName());
			existingTypeEntreprise.setDescription(typeentreprise.getDescription());

			typeentreprise = typeEntrepriseRepository.save(existingTypeEntreprise);

			result.rejectValue("code", null,
					"There is already a typeentreprise saved with the same code. Update Done");
		}

		if (result.hasErrors()) {
			model.addAttribute("typeentreprise", typeentreprise);
			return "/admin/form-types-entreprises";
		}
		typeentreprise = typeEntrepriseRepository.save(typeentreprise);

		model.addAttribute("typeentreprises", typeEntrepriseRepository.findAll());
		return "redirect:/admin/form-types-entreprises?success";

	}

	@GetMapping("/delete-types-entreprises")
	public String deleteCategorie(@RequestParam("typeentrepriseId") long typeentrepriseId, Model model) {
		TypeEntreprise teToDelete = typeEntrepriseRepository.findById(typeentrepriseId).get();
		typeEntrepriseRepository.delete(teToDelete);
		model.addAttribute("typeentreprise", new TypeEntreprise());
		model.addAttribute("typesentreprises", typeEntrepriseRepository.findAll());
		return "/admin/form-types-entreprises";
	}

}
