package com.master.examples.drools.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder(access = AccessLevel.PUBLIC, toBuilder = true)
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

    @Column(name = "starting_sum")
    private Double productSum;

    public Product() {
    }

    public Product(long id, String productName, ProductType productType, Set<InsuranceProduct> insuranceSet, Double productSum) {
        this.id = id;
        this.productName = productName;
        this.productType = productType;
        this.insuranceSet = insuranceSet;
        this.productSum = productSum;
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


    public Double getProductSum() {
        return productSum;
    }

    public void setProductSum(Double productSum) {
        this.productSum = productSum;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productType=" + productType +
                ", insuranceSet=" + insuranceSet +
                ", productSum=" + productSum +
                '}';
    }

    public Product(String productName, ProductType productType) {
        this.productName = productName;
        this.productType = productType;
    }
}
