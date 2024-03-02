package org.formation.controller.rest;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.formation.model.Role;
import org.formation.repositories.RoleRepository;
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
@RequestMapping("/api/roles")
public class RoleRestController {

	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("{name}")
	public Role findByName(@PathVariable("role") String name) {
		return roleRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Rôle inconnu :"+name));
	}
	
	@PostMapping
	ResponseEntity<Role> createRole(@Valid @RequestBody Role role) {
		
		role = roleRepository.save(role);
		
		return new ResponseEntity<Role>(role,HttpStatus.CREATED);
		
	}
}
