package com.master.examples.drools.repository;

import com.master.examples.drools.model.InsuranceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceProductRepository extends JpaRepository<InsuranceProduct, Long> {
}
