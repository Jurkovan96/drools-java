package com.master.examples.drools.repository;

import com.master.examples.drools.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
