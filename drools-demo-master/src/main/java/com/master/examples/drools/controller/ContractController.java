package com.master.examples.drools.controller;

import com.master.examples.drools.service.ContarctServiceImp;
import com.master.examples.drools.domain.Contract;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
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

    @Autowired
    public ContractController(ContarctServiceImp contarctServiceImp, KieContainer kieContainer) {
        this.contarctServiceImp = contarctServiceImp;
        this.kieContainer = kieContainer;
    }

    @GetMapping("/contracts")
    public Collection<?> getAllContracts(@RequestParam(name = "limit", required = false) Integer limit) {
        return contarctServiceImp.getAll()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<?> getContractById(@PathVariable long contractId) {
        return contarctServiceImp
                .getById(contractId)
                .map(contract -> ResponseEntity.ok().body(contract))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @GetMapping("/drl/contract/{contractId}")
    public ResponseEntity<?> getContarctWithRules(@PathVariable long contractId) {
        Contract contract = (Contract) contarctServiceImp.getById(contractId).get();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(contract);
        kieSession.fireAllRules();
        kieSession.dispose();
        return ResponseEntity.ok().body(contract);
    }

}
