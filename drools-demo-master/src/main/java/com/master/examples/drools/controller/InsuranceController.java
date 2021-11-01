package com.master.examples.drools.controller;

import com.master.examples.drools.service.InsuranceSerineImp;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class InsuranceController {

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
        return insuranceSerineImp
                .getAll()
                .stream()
                .filter(Objects::nonNull)
                .peek(insurance -> {
                    kieSession.insert(insurance);
                    kieSession.fireAllRules();
                    kieSession.dispose();
                })
                .collect(Collectors.toList());
    }
}
