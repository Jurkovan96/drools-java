package com.master.examples.drools.controller;

import com.master.examples.drools.service.ContarctServiceImp;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ContractController {

    private final ContarctServiceImp contarctServiceImp;

    private final KieContainer kieContainer;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ContractController(ContarctServiceImp contarctServiceImp, KieContainer kieContainer) {
        this.contarctServiceImp = contarctServiceImp;
        this.kieContainer = kieContainer;
    }

    @GetMapping("/contracts")
    public Collection<?> getAllContracts(@RequestParam(name = "limit", required = false) Integer limit) {
        KieSession kieSession = kieContainer.newKieSession();
        return contarctServiceImp
                .getAll()
                .stream()
                .peek(contract -> {
                    kieSession.insert(contract);
                    kieSession.fireAllRules();
                    kieSession.dispose();
                    logger.info("KieSession {}", kieSession.getClass().getName());
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<?> getContractById(@PathVariable long contractId) {
        KieSession kieSession = kieContainer.newKieSession();
        return contarctServiceImp
                .getById(contractId)
                .map(contract -> {
                    kieSession.insert(contract);
                    kieSession.fireAllRules();
                    kieSession.dispose();
                    return ResponseEntity.ok().body(contract);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build());
    }
}
