package com.master.examples.drools.repository;

import com.master.examples.drools.domain.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
