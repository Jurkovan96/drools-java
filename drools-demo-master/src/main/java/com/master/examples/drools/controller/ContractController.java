package com.master.examples.drools.controller;

import com.master.examples.drools.model.Contract;
import com.master.examples.drools.model.NotificationPayment;
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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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
        for (Object obj : contarctServiceImp.getAll()) {
            kieSession.insert(obj);
            kieSession.insert("allContracts");
            kieSession.fireAllRules();
        }
        kieSession.dispose();
        return contarctServiceImp.getAll();
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<?> getContractById(@PathVariable long contractId,
                                             @RequestParam(name = "insert", required = false) boolean insert) {
        KieSession kieSession = kieContainer.newKieSession();
        Set<NotificationPayment> notificationSet = new HashSet<>();
        kieSession.setGlobal("newSet", notificationSet);
        return contarctServiceImp
                .getById(contractId)
                .map(contract -> {
                    kieSession.insert(contract);
                    kieSession.insert("contract");
                    kieSession.insert("notifications");
                    kieSession.fireAllRules();
                    kieSession.dispose();
                    if (insert) {
                        contarctServiceImp.save(contract);
                    }
                    return ResponseEntity.ok().body(contract);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @PostMapping("/add/new/contract")
    public ResponseEntity<?> getNewContractFromDRL(@RequestParam(name = "insert", required = false) boolean insert) {
        KieSession kieSession = kieContainer.newKieSession();
        Contract contract = new Contract();
        kieSession.insert(contract);
        kieSession.insert("start");
        kieSession.insert(1);
        kieSession.fireAllRules();
        kieSession.dispose();
        return ResponseEntity.status(HttpStatus.CREATED).body(contract);
    }

}
