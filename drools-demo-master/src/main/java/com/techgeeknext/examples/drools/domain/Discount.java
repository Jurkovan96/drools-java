package com.techgeeknext.examples.drools.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Discount {

    @Id
    private long id;

    private Double sum;

    private Double percentage;

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Discount(Double sum, Double percentage) {
        this.sum = sum;
        this.percentage = percentage;
    }
}
