package com.master.examples.drools.controller;

import com.master.examples.drools.service.PersonServiceImp;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonServiceImp personServiceImp;

    private final KieContainer kieContainer;

    @Autowired
    public PersonController(PersonServiceImp personServiceImp, KieContainer kieContainer) {
        this.personServiceImp = personServiceImp;
        this.kieContainer = kieContainer;
    }

    @GetMapping("/active_contracts/person/{personId}")
    public ResponseEntity<?> getPersonActiveContracts(@PathVariable long personId) {
        KieSession kieSession = kieContainer.newKieSession();
        return personServiceImp.getById(personId)
                .map(person -> {
                    kieSession.insert(person);
                    kieSession.fireAllRules();
                    kieSession.dispose();
                    return ResponseEntity.ok().body(person);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @GetMapping("/contracts/person/{personId}")
    public ResponseEntity<?> getPersonContracts(@PathVariable long personId) {
        return personServiceImp.getById(personId)
                .map(person ->
                        ResponseEntity.ok().body(person))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build());
    }
}
