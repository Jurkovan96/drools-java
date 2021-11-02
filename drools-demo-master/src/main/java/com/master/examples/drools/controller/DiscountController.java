package com.master.examples.drools.controller;

import com.master.examples.drools.service.DiscountServiceImp;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class DiscountController {

    private final DiscountServiceImp discountServiceImp;

    private final KieContainer kieContainer;

    @Autowired
    public DiscountController(DiscountServiceImp discountServiceImp, KieContainer kieContainer) {
        this.discountServiceImp = discountServiceImp;
        this.kieContainer = kieContainer;
    }

    @GetMapping("/discounts/contract/{contractId}")
    public Collection<?> getDiscountsByContract(@PathVariable long contractId) {
        return discountServiceImp.getDiscountPerContract(contractId);
    }

}
