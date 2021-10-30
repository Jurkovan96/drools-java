package com.techgeeknext.examples.drools.controller;

import com.techgeeknext.examples.drools.domain.Contract;
import com.techgeeknext.examples.drools.domain.FDRequest;
import com.techgeeknext.examples.drools.domain.Insurance;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RestController
public class FixedDepositRateController {

    private final KieContainer kieContainer;

    public FixedDepositRateController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @RequestMapping(value = "/getFDInterestRate", method = RequestMethod.GET, produces = "application/json")
    public FDRequest getQuestions(@RequestParam(required = true) String bank, @RequestParam(required = true) Integer durationInYear) {
        KieSession kieSession = kieContainer.newKieSession();
        FDRequest fdRequest = new FDRequest(bank, durationInYear);
        kieSession.insert(fdRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return fdRequest;
    }

    @GetMapping("/insurance/{insuranceId}")
    public ResponseEntity<Contract> getContractById(@PathVariable Long insuranceId) throws ParseException {
        Contract contract = new Contract(insuranceId);
        contract.setEndDate("25/10/2021");
        contract.setDateStart("10/10/2021");
        Insurance insurance = new Insurance(contract, "A20", Insurance.InsuranceType.VEHICLE, 200.5);
        System.out.println(insurance.isActivePayment());
        return ResponseEntity.ok().build();
    }
}
