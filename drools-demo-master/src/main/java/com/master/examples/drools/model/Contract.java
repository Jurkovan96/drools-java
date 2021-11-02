package com.master.examples.drools.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class Contract {

    public enum ContractType {
        SMALL, MEDIUM, LONG
    }

    @Transient
    private boolean isActive;

    @Transient
    private boolean hasInsurance;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @OneToMany(mappedBy = "contract", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnoreProperties("contact")
    //@JsonIgnore
    private final Set<Insurance> insuranceSet = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    private Date dateStart;

    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonIgnoreProperties({"contractList", "activeContracts"})
    private Person holder;

    public Contract(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        Date date = new Date();
        if (dateStart != null && endDate != null) {
            return date.after(dateStart) && date.before(endDate);
        } else {
            return false;
        }
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

    public String getDateStart() {
        if (dateStart != null) {
            return new SimpleDateFormat("dd/MM/yyyy").format(dateStart).toString();
        } else {
            return "No date present!";
        }
    }

    public void setDateStart(String dateStart) {
        try {
            this.dateStart = new SimpleDateFormat("dd/MM/yyyy").parse(dateStart);
        } catch (ParseException parseException) {
            this.endDate = new Date();
        }
    }

    public Date getContractEndDate() {
        return endDate;
    }

    public String getEndDate() {
        if (endDate != null) {
            return new SimpleDateFormat("dd/MM/yyyy").format(endDate).toString();
        } else {
            return "No date present!";
        }
    }

    public void setEndDate(String endDate) {
        try {
            this.endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        } catch (ParseException parseException) {
            this.endDate = new Date();
        }
    }

    public Set<Insurance> getInsuranceSet() {
        return insuranceSet;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Person getHolder() {
        return holder;
    }

    public void setHolder(Person holder) {
        this.holder = holder;
    }

    public Contract() {
    }

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }
}
