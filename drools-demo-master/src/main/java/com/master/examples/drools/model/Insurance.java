package com.master.examples.drools.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Insurance {

    @Id
    private long id;

    public enum InsuranceType {
        LIFE, PROPERTY, VEHICLE
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    private String number;

    @Enumerated(EnumType.STRING)
    private InsuranceType insuranceType;

    @ManyToMany
    @JoinTable(name = "discount_insurance",
            joinColumns = @JoinColumn(name = "discount_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("insuranceSet")
    private Set<Discount> discountSet = new HashSet<>();

    private Double sumInsured;

    private boolean activePayment;

    @OneToMany(mappedBy = "insurance")
    @JsonIgnoreProperties("insurance")
    private Set<InsuranceProduct> insuranceProducts = new HashSet<>();

    public Insurance(Contract contract, String number, InsuranceType insuranceType, Double sumInsured) {
        this.number = number;
        this.insuranceType = insuranceType;
        this.sumInsured = sumInsured;
        this.activePayment = false;
        this.contract = contract;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }

    public Double getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(Double sumInsured) {
        this.sumInsured = sumInsured;
    }

    public boolean isActivePayment() {
        activePayment = contract.isActive();
        return activePayment;
    }

    public void setActivePayment(boolean activePayment) {
        this.activePayment = activePayment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Discount> getDiscountSet() {
        return discountSet;
    }

    public void setDiscountSet(Set<Discount> discountSet) {
        this.discountSet = discountSet;
    }

    public Set<InsuranceProduct> getInsuranceProducts() {
        return insuranceProducts;
    }

    public void setInsuranceProducts(Set<InsuranceProduct> insuranceProducts) {
        this.insuranceProducts = insuranceProducts;
    }

    public Insurance() {
    }
}
