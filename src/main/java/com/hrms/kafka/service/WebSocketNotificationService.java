package com.hrms.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketNotificationService {
	private static final Logger log = LoggerFactory.getLogger(WebSocketNotificationService.class);
	private final SimpMessagingTemplate messagingTemplate;

	@Autowired
	public WebSocketNotificationService(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	public void sendNotificationToUser(Long userId, String message) {
		String userDestination = "/topic/notifications/" + userId;
		messagingTemplate.convertAndSend(userDestination, message);
		log.info("WebSocket notification sent to user {}: {}", userId, message);
	}
}
