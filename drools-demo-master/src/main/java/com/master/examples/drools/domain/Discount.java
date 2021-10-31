package com.master.examples.drools.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Discount {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private Double percentage;

    @ManyToMany
    @JoinTable(name = "discount_insuranceSet",
            joinColumns = @JoinColumn(name = "discount_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_id", referencedColumnName = "id"))
    private Set<Insurance> insuranceSet = new HashSet<>();

    public void setInsuranceSet(Set<Insurance> insuranceSet) {
        this.insuranceSet = insuranceSet;
    }

    public Set<Insurance> getInsuranceSet() {
        return insuranceSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Discount() {
    }
}
