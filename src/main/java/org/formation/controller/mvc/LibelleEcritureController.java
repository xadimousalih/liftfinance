package org.formation.controller.mvc;

import org.formation.repositories.LibelleEcritureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping(value = "pages")
public class LibelleEcritureController {

    @Autowired
    private LibelleEcritureRepository libelleEcritureRepository;


    @GetMapping(value = "/listeLibelleEcriture")
    public String populateList(Model model) {
        List<String> options;
        options = libelleEcritureRepository.findAllNames();
        model.addAttribute("options", options);
        return "listeLibelleEcriture";
    }
}
