package com.master.examples.drools.service;

import com.master.examples.drools.repository.ContractRepository;
import com.master.examples.drools.repository.PersonRepository;
import com.master.examples.drools.service.serviceImp.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PersonServiceImp implements CRUDService {

    private final PersonRepository personRepository;

    private final ContractRepository contractRepository;

    @Autowired
    public PersonServiceImp(PersonRepository personRepository, ContractRepository contractRepository) {
        this.personRepository = personRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public Collection<?> getAll() {
        return null;
    }

    @Override
    public Optional<?> getById(long id) {
        return personRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {

    }
}
