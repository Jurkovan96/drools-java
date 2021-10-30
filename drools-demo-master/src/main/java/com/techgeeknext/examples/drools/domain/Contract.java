package com.techgeeknext.examples.drools.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Contract {

    public enum ContractType {
        SMALL, MEDIUM, LONG
    }

    @Column(name = "active")
    private boolean isActive;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    @OneToMany(mappedBy = "contract", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Insurance> insuranceList;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Contract.class)
    public Set<Discount> discounts = new HashSet<>();

    @Enumerated(EnumType.STRING)
    public ContractType contractType;

    public Date dateStart;

    public Date endDate;

    public Contract(Long id) {
        this.id = id;
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
