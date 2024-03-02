package org.formation.repositories;

import java.util.Optional;

import org.formation.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);
	
	
}
