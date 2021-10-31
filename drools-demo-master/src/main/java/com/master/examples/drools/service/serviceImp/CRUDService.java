package com.master.examples.drools.service.serviceImp;

import java.util.Collection;
import java.util.Optional;

public interface CRUDService {

    Collection<?> getAll();

    Optional<?> getById(long id);

    void deleteById(long id);
}
