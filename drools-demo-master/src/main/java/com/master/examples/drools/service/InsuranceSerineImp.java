package com.master.examples.drools.service;

import com.master.examples.drools.repository.InsuranceRepository;
import com.master.examples.drools.service.serviceImp.CRUDService;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class InsuranceSerineImp implements CRUDService {

    private final InsuranceRepository insuranceRepository;

    private final KieContainer kieContainer;

    @Autowired
    public InsuranceSerineImp(InsuranceRepository insuranceRepository, KieContainer kieContainer) {
        this.insuranceRepository = insuranceRepository;
        this.kieContainer = kieContainer;
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
}
