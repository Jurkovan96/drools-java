package com.master.examples.drools.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "insurance_products")
public class InsuranceProduct {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    @JsonIgnoreProperties("insuranceProducts")
    private Insurance insurance;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("insuranceSet")
    private Product product;

    @Column(name = "sum_insured")
    private double sumInsured;

    public InsuranceProduct() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(double sumInsured) {
        this.sumInsured = sumInsured;
    }
}
