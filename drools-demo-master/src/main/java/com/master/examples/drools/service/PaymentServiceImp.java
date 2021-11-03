package com.master.examples.drools.service;

import com.master.examples.drools.model.Contract;
import com.master.examples.drools.model.Payment;
import com.master.examples.drools.repository.ContractRepository;
import com.master.examples.drools.service.serviceImp.CRUDService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PaymentServiceImp implements CRUDService {

    private final ContractRepository contractRepository;

    private final KieContainer kieContainer;

    @Autowired
    public PaymentServiceImp(ContractRepository contractRepository, KieContainer kieContainer) {
        this.contractRepository = contractRepository;
        this.kieContainer = kieContainer;
    }

//    public Collection<Payment> getAllPaymentRequestsByContractId(long contractId){
//        KieSession kieSession = kieContainer.newKieSession();
//        if(contractRepository.findById(contractId).isPresent()){
//            Contract contract = contractRepository.findById(contractId).get();
//            kieSession.insert(contract);
//            kieSession.fireAllRules();
//            kieSession.dispose();
//            contract.getInsuranceSet().forEach(insurance -> {
//
//            });
//        }
//    }

    @Override
    public Collection<?> getAll() {
        return null;
    }

    @Override
    public Optional<?> getById(long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {

    }
}
