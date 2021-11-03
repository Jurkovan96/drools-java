package com.master.examples.drools.controller;

import com.master.examples.drools.model.Insurance;
import com.master.examples.drools.service.InsuranceSerineImp;
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
public class InsuranceController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final InsuranceSerineImp insuranceSerineImp;

    private final KieContainer kieContainer;

    @Autowired
    public InsuranceController(InsuranceSerineImp insuranceSerineImp, KieContainer kieContainer) {
        this.insuranceSerineImp = insuranceSerineImp;
        this.kieContainer = kieContainer;
    }

    @GetMapping("/insurances")
    public Collection<?> getAllInsurance() {
        KieSession kieSession = kieContainer.newKieSession();
//        return insuranceSerineImp
//                .getAll()
//                .stream()
//                .filter(Objects::nonNull)
//                .peek(insurance -> {
//                    kieSession.insert(insurance);
////                    kieSession.insert(logger);
//                    kieSession.fireAllRules();
//                    kieSession.dispose();
//                })
//                .collect(Collectors.toList());
        for (Object insurance : insuranceSerineImp.getAll()) {
            kieSession.insert(insurance);
        }
        kieSession.fireAllRules();
        kieSession.dispose();
        return insuranceSerineImp.getAll();
    }

    @PostMapping("/new/insurance/contract/{contractId}")
    public ResponseEntity<?> createNewInsurance(@PathVariable long contractId,
                                                @RequestBody Insurance insurance,
                                                @RequestParam(name = "insert", required = false) boolean insert) {
        if (insert) {
            insuranceSerineImp.save(insuranceSerineImp.addInsurance(insurance, contractId).get());
        }
        return insuranceSerineImp.addInsurance(insurance, contractId)
                .map(insuranceObj -> ResponseEntity.status(HttpStatus.CREATED).body(insuranceObj))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
