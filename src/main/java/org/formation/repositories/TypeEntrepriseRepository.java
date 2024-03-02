package org.formation.repositories;

import java.util.Optional;

import org.formation.model.TypeEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeEntrepriseRepository extends JpaRepository<TypeEntreprise, Long> {
	
	TypeEntreprise findByCode(String code);
	Optional<TypeEntreprise> findByName(String name);

	
}
