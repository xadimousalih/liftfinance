package org.formation.controller.mvc;

import org.formation.model.Ecriture;
import org.formation.model.MoisEnum;
import org.formation.repositories.EcritureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "pages")
public class FormEcritureListController {

    @Autowired
    private EcritureRepository ecritureRepository;

    @GetMapping(value = "form-list-ecritures")
    public ModelMap mmListEcritures(Model model,
                                    @RequestParam(name="page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<Ecriture> ecrituresPage = ecritureRepository.findAll(PageRequest.of(page, size));
        model.addAttribute("ecritures", ecrituresPage.getContent());
        model.addAttribute("pages", new int[ecrituresPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return new ModelMap();
    }



}
