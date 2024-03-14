package org.formation.controller.mvc;

import org.formation.model.Categorie;
import org.formation.model.LibelleEcriture;
import org.formation.repositories.CategorieRepository;
import org.formation.repositories.LibelleEcritureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LibelleEcritureRestController {

    @Autowired
    private LibelleEcritureRepository libelleEcritureRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping(value = "listeLibelleEcriture")
    public String afficherListeLibelleEcriture(Model model) {
        model.addAttribute("nomsLibelleEcriture", libelleEcritureRepository.findAllNames());
        return "listeLibelleEcriture"; // Nom du fichier HTML
    }
}
