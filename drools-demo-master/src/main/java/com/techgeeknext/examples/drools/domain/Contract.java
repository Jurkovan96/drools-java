package com.techgeeknext.examples.drools.domain;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Contract {

    public enum ContractType {
        SMALL, MEDIUM, LONG
    }

    private boolean isActive;

    private Long id;

    private List<Insurance> insuranceList;

    public List<Discount> discounts;

    public ContractType contractType;

    public Date dateStart;

    public Date endDate;

    public Contract(Long id) {
        this.id = id;
        this.insuranceList = new ArrayList<>();
        this.isActive = false;
    }

    public List<Insurance> getAllActiveInsurance() {
        return insuranceList.stream()
                .filter(Insurance::isActivePayment)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean isActive() {
        Date date = new Date();
        return date.after(dateStart) && date.before(endDate);
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Insurance> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(List<Insurance> insuranceList) {
        this.insuranceList = insuranceList;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        try {
            this.dateStart = new SimpleDateFormat("dd/MM/yyyy").parse(dateStart);
        } catch (ParseException parseException) {
            this.endDate = new Date();
        }
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        try {
            this.endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        } catch (ParseException parseException) {
            this.endDate = new Date();
        }
    }
}
