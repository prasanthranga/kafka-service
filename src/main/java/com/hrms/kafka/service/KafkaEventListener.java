package com.hrms.kafka.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.hrms.kafka.config.TenantContext;
import com.hrms.kafka.constants.KafkaTopics;

@Service
public class KafkaEventListener {
	private final Logger log = LoggerFactory.getLogger(KafkaEventListener.class);

	private final WebSocketService webSocketService;

	public KafkaEventListener(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

	@KafkaListener(topics = "#{T(com.hrms.kafka.constants.KafkaTopics).values().![getTopicName()]}", groupId = "payroll-processing-group")
	public void consumeEvent(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topicName) {
		log.info("Received event from topic: {} for tenant: {} with payload: {}", topicName, message);
		try {
			String tenantId = TenantContext.getTenantId();
			System.out.println("tenat id in kafka event listner "+TenantContext.getTenantId());
			// Process event based on topic dynamically
			if (KafkaTopics.PAYROLL_PAYPROCESS.getTopicName().equals(topicName)) {
				processPayrollEvent(tenantId, topicName);
			} /*
				 * else if (KafkaTopics.ATTENDANCE_MARKED.getTopicName().equals(topic)) {
				 * processAttendanceEvent(tenantId, payload); } else if
				 * (KafkaTopics.LEAVE_REQUESTED.getTopicName().equals(topic)) {
				 * processLeaveEvent(tenantId, payload); }
				 */ else {
				log.warn("No handler found for topic: {}", topicName);
			}
		} catch (Exception e) {
			log.error("Error processing event for topic: {}", topicName, e);
		}
	}

	private void processPayrollEvent(String tenantId,String topicName) {
		log.info("Processing payroll event for tenant: {} ", tenantId);
		// Send real-time WebSocket alert to the relevant topic
		String message ="";
		List<Long> userIds = new ArrayList<>();
		webSocketService.sendMessageToTopic(topicName, userIds, message);
		// Call Payroll Service
	}

	private void processAttendanceEvent(String tenantId, String employeeId) {
		log.info("Processing attendance event for tenant: {} and employeeId: {}", tenantId, employeeId);
		// Call Attendance Service
	}

	private void processLeaveEvent(String tenantId, String leaveId) {
		log.info("Processing leave event for tenant: {} and leaveId: {}", tenantId, leaveId);
		// Call Leave Service
	}
}
