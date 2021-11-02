package com.master.examples.drools.controller;

import com.master.examples.drools.repository.NotificationRepository;
import com.master.examples.drools.service.NotificationServiceImp;
import org.kie.api.internal.utils.KieService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NotificationController {

    private final NotificationServiceImp notificationServiceImp;

    private final KieContainer kieContainer;

    @Autowired
    public NotificationController(NotificationServiceImp notificationServiceImp, KieContainer kieContainer) {
        this.notificationServiceImp = notificationServiceImp;
        this.kieContainer = kieContainer;
    }

}
