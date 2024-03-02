package org.formation.controller.mvc;

import java.util.List;

import org.formation.model.Person;
import org.formation.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "persons")
    public List<Person> findAll() {
        personService.addPerson();
        return personService.getAllPersons();
    }

}
