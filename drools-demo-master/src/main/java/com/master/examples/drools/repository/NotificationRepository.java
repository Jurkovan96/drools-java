package com.master.examples.drools.repository;

import com.master.examples.drools.model.NotificationPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationPayment, Long> {
}
