package org.formation.repositories;

import java.util.Optional;

import org.formation.model.Ecriture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcritureRepository extends JpaRepository<Ecriture, Long> {
	
	Ecriture findByNumero(String numero);

	
	
}
