package org.formation.repositories;

import java.util.Optional;

import org.formation.model.SecteurActivite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecteurActiviteRepository extends JpaRepository<SecteurActivite, Long> {
	
	SecteurActivite findByCode(String code);
	Optional<SecteurActivite> findByName(String name);

	
}
