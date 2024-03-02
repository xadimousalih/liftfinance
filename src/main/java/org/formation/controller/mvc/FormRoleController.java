package org.formation.controller.mvc;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.formation.model.Role;
import org.formation.repositories.RoleRepository;
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
public class FormRoleController {

    @Autowired
	private RoleRepository roleRepository;
    
    
    @GetMapping(value = "form-roles")
    public ModelMap mmFormElements(Model model) {
    	model.addAttribute("role", new Role());
        return new ModelMap();
    }
    
    
    @ModelAttribute("roles")
    public List<Role> messages() {
        return roleRepository.findAll();
    }

	@RequestMapping(value = "find-roles")
	public String findRoles(Model model) {

		model.addAttribute("roles", roleRepository.findAll());
		return "roles";
	}

	@GetMapping("/edit-role")
	public String editRole(@RequestParam("roleId") long roleId, Model model) {
		model.addAttribute("role",
				roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException()));
		return "/admin/form-edit-role";
	}

	@PostMapping(path = "/save-role", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String save(@Valid Role role, Model model, BindingResult result) {
		
		Role existingRole = roleRepository.findByName(role.getName()).orElse(null);
		
		  if(existingRole != null && existingRole.getName() != null && !existingRole.getName().isEmpty()){
			  
			  existingRole.setName(role.getName());

				role = roleRepository.save(existingRole);

				result.rejectValue("reference", null,
						"There is already a role saved with the same reference. Update Done");		
		    }

		    if(result.hasErrors()){
		      model.addAttribute("role", role);
		      return "/admin/form-roles";
		    }
			role = roleRepository.save(role);

		model.addAttribute("roles", roleRepository.findAll());
		return "redirect:/admin/form-roles?success";

	}

}
