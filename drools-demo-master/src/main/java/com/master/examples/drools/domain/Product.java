package com.master.examples.drools.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    public enum ProductType {
        VEHICLE, HOUSE, LIFE
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private String productName;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties("product")
    private Set<InsuranceProduct> insuranceSet = new HashSet<>();

    @Transient
    private Double minSum;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Set<InsuranceProduct> getInsuranceSet() {
        return insuranceSet;
    }

    public void setInsuranceSet(Set<InsuranceProduct> insuranceSet) {
        this.insuranceSet = insuranceSet;
    }

    public Double getMinSum() {
        return minSum;
    }

    public void setMinSum(Double minSum) {
        this.minSum = minSum;
    }
}
