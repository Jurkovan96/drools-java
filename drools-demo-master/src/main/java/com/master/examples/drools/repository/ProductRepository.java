package com.master.examples.drools.repository;

import com.master.examples.drools.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
