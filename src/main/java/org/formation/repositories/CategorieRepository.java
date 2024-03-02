package org.formation.repositories;

import java.util.Optional;

import org.formation.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

	Categorie findByReference(String reference);
	Optional<Categorie> findByName(String name);
	
	
}
