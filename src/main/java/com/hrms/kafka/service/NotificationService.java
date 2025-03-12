package com.hrms.kafka.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	private final SimpMessagingTemplate messagingTemplate;

	// Simulated storage for undelivered notifications (Use Redis or DB in
	// production)
	private final Map<Long, List<String>> undeliveredNotifications = new HashMap<>();

	public NotificationService(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	// Store notifications for offline users
	public void storeNotification(Long userId, String message) {
		undeliveredNotifications.computeIfAbsent(userId, k -> new ArrayList<>()).add(message);
	}


	public void sendNotificationToGroup(String topic, String message) {
		messagingTemplate.convertAndSend("/topic/" + topic, message);
	}

	// Send notification to a specific user
	public void sendNotificationToUser(Long userId, String message) {
		boolean isUserOnline = checkUserOnline(userId);

		if (isUserOnline) {
			messagingTemplate.convertAndSendToUser(userId.toString(), "/queue/notifications", message);
		} else {
			storeNotification(userId, message); // Store if offline
		}
	}

	// Send all stored messages when user comes online
	public void sendStoredNotifications(Long userId) {
		List<String> messages = undeliveredNotifications.remove(userId);
		if (messages != null) {
			for (String message : messages) {
				messagingTemplate.convertAndSendToUser(userId.toString(), "/queue/notifications", message);
			}
		}
	}

	// Simulate user presence check (replace with actual logic)
	private boolean checkUserOnline(Long userId) {
		return false; // Assume user is offline; implement actual check
	}
}
