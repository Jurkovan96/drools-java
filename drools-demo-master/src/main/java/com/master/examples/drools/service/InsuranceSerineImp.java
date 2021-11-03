package com.master.examples.drools.service;

import com.master.examples.drools.model.Contract;
import com.master.examples.drools.model.Insurance;
import com.master.examples.drools.repository.InsuranceRepository;
import com.master.examples.drools.service.serviceImp.CRUDService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class InsuranceSerineImp implements CRUDService {

    private final InsuranceRepository insuranceRepository;

    private final KieContainer kieContainer;

    private final ContarctServiceImp contarctServiceImp;

    @Autowired
    public InsuranceSerineImp(InsuranceRepository insuranceRepository, KieContainer kieContainer, ContarctServiceImp contarctServiceImp) {
        this.insuranceRepository = insuranceRepository;
        this.kieContainer = kieContainer;
        this.contarctServiceImp = contarctServiceImp;
    }

    @Override
    public Collection<?> getAll() {
        return insuranceRepository.findAll();
    }

    @Override
    public Optional<?> getById(long id) {
        return insuranceRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {

    }

    public Optional<Insurance> addInsurance(Insurance insurance, long contractId) {
        if (contarctServiceImp.getById(contractId).isPresent()) {
            insurance.setContract((Contract) contarctServiceImp.getById(contractId).get());
            KieSession kieSession = kieContainer.newKieSession();
            kieSession.insert(insurance);
            kieSession.fireAllRules();
            kieSession.dispose();
            return Optional.of(insurance);
        } else {
            return Optional.empty();
        }
    }

    public void save(Insurance insuranceObj) {
        insuranceRepository.save(insuranceObj);
    }
}
