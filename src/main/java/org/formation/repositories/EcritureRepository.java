package org.formation.repositories;

import java.util.List;


import org.formation.model.Ecriture;
import org.formation.model.MoisEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcritureRepository extends JpaRepository<Ecriture, Long> {


	Ecriture findByNumero(String numero);
	Page<Ecriture> findByMois(MoisEnum mois, Pageable pageable);
	Page<Ecriture> findByCategorie_Reference(String reference, Pageable pageable);
	Page<Ecriture> findByMoisAndCategorie_Reference(MoisEnum mois, String reference, Pageable pageable);
	Page<Ecriture> findBylibelleContainsIgnoreCase(String libelle, Pageable pageable);

}
