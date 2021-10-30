package com.techgeeknext.examples.drools.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {

    private Long id;

    private String account;

    private List<Contract> contractList;

    public Person(Long id, String account, List<Contract> contractList) {
        this.id = id;
        this.account = account;
        this.contractList = contractList;
    }

    public List<Contract> getActiveContracts() {
        return contractList.stream().
                filter(Contract::isActive).
                collect(Collectors.toCollection(ArrayList::new));
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

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }
}
