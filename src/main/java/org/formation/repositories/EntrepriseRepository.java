package org.formation.repositories;

import java.util.Optional;

import org.formation.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
	
	Entreprise findByCode(String code);
	Optional<Entreprise> findByName(String name);
	


	
	
}
