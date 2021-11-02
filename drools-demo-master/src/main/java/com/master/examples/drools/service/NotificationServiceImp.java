package com.master.examples.drools.service;

import com.master.examples.drools.repository.NotificationRepository;
import com.master.examples.drools.service.serviceImp.CRUDService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImp implements CRUDService {

    private final NotificationRepository notificationRepository;

    private final KieContainer kieContainer;

    @Autowired
    public NotificationServiceImp(NotificationRepository notificationRepository, KieContainer kieContainer) {
        this.notificationRepository = notificationRepository;
        this.kieContainer = kieContainer;
    }

    @Override
    public Collection<?> getAll() {
        KieSession kieSession = kieContainer.newKieSession();
        return notificationRepository
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .peek(notification -> {
                    kieSession.insert(notification);
                    kieSession.fireAllRules();
                    kieSession.dispose();
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<?> getById(long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {

    }
}
