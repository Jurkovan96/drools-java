package com.master.examples.drools.service;

import com.master.examples.drools.model.Contract;
import com.master.examples.drools.model.Discount;
import com.master.examples.drools.model.Insurance;
import com.master.examples.drools.repository.ContractRepository;
import com.master.examples.drools.repository.DiscountRepository;
import com.master.examples.drools.service.serviceImp.CRUDService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImp implements CRUDService {

    private final DiscountRepository discountRepository;

    private final ContarctServiceImp contarctServiceImp;

    private final ContractRepository contractRepository;

    private final KieContainer kieContainer;

    @Autowired
    public DiscountServiceImp(DiscountRepository discountRepository, ContarctServiceImp contarctServiceImp, ContractRepository contractRepository, KieContainer kieContainer) {
        this.discountRepository = discountRepository;
        this.contarctServiceImp = contarctServiceImp;
        this.contractRepository = contractRepository;
        this.kieContainer = kieContainer;
    }

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

    public Collection<Set<Discount>> getDiscountPerContract(long contractId) {
        KieSession kieSession = kieContainer.newKieSession();
        if (contractRepository.findById(contractId).isPresent()) {
            Contract contract = contractRepository.findById(contractId).get();
            kieSession.insert(contract);
            kieSession.fireAllRules();
            kieSession.dispose();
            return contract.getInsuranceSet()
                    .stream()
                    .map(Insurance::getDiscountSet)
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            return Collections.emptyList();
        }
    }
}
