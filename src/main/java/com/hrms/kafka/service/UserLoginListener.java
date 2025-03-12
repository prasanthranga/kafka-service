package com.hrms.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserLoginListener {

    private final NotificationService notificationService;

    public UserLoginListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "user-login", groupId = "notification-group")
    public void handleUserLogin(Long userId) {
        System.out.println("User logged in, sending stored notifications: " + userId);

        // Send stored notifications when user logs in
        notificationService.sendStoredNotifications(userId);
    }
}
