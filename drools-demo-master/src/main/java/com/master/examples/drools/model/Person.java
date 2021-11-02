package com.master.examples.drools.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Person {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "holder_name")
    private String account;

    @OneToMany(mappedBy = "holder", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("holder")
    private Set<Contract> contractList = new HashSet<>();

    public Person(Long id, String account) {
        this.id = id;
        this.account = account;
    }

    public Person() {

    }

    public Set<Contract> getActiveContracts() {
        return contractList.stream().
                filter(Contract::isActive).
                collect(Collectors.toCollection(HashSet::new));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Set<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(Collection contractList) {
        this.contractList = (Set<Contract>) contractList;
    }
}
