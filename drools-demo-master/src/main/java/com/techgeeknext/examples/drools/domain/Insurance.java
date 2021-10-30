package com.techgeeknext.examples.drools.domain;

public class Insurance {

    public enum InsuranceType {
        LIFE, PROPERTY, VEHICLE
    }

    private Contract contract;

    private String number;

    private InsuranceType insuranceType;

    private Double sumInsured;

    private boolean activePayment;

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
}
