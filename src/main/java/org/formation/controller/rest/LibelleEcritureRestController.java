package org.formation.controller.rest;

import org.formation.model.Categorie;
import org.formation.model.LibelleEcriture;
import org.formation.repositories.CategorieRepository;
import org.formation.repositories.LibelleEcritureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libelle-ecriture")
public class LibelleEcritureRestController {

    @Autowired
    private LibelleEcritureRepository libelleEcritureRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping("/by-categorie/{categorieId}")
    public ResponseEntity<List<LibelleEcriture>> getLibelleEcritureByCategorie(@PathVariable Long categorieId) {
        Optional<Categorie> categorieOptional = categorieRepository.findById(categorieId);
        if (categorieOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Categorie categorie = categorieOptional.get();
        Optional<List<LibelleEcriture>> libelleEcritureListOptional = libelleEcritureRepository.findByCategorie(categorie);
        if (libelleEcritureListOptional.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(libelleEcritureListOptional.get());
    }
}
