package org.formation.repositories;

import org.formation.model.Categorie;
import org.formation.model.LibelleEcriture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibelleEcritureRepository extends JpaRepository<LibelleEcriture, Long> {
	Optional<List<LibelleEcriture>> findByCategorie(Categorie categorie);

}
