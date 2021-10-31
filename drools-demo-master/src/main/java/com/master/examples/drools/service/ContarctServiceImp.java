package com.master.examples.drools.service;

import com.master.examples.drools.repository.ContractRepository;
import com.master.examples.drools.service.serviceImp.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ContarctServiceImp implements CRUDService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContarctServiceImp(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Collection<?> getAll() {
        return contractRepository.findAll();
    }

    @Override
    public Optional<?> getById(long id) {
        return contractRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        contractRepository.deleteById(id);
    }
}
