package com.hrms.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.hrms.kafka.config.TenantContext;
import com.hrms.kafka.constants.KafkaTopicConstants;

@Component
public class PayrollKafkaConsumer {
	private static final Logger log = LoggerFactory.getLogger(PayrollKafkaConsumer.class);

	private final NotificationService notificationService;

	@Autowired
	public PayrollKafkaConsumer(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	/*
	 * @KafkaListener(topics = KafkaTopicConstants.PAYROLL_PROCESS, groupId =
	 * "payroll-group") public void consumePayrollEvent(@Payload String
	 * status, @Header("X-Tenant-ID") String tenantId) {
	 * log.info("Received Payroll Event for Tenant: {} - Status: {}", tenantId,
	 * status); processPayrollEvent(tenantId, status); }
	 */

	@KafkaListener(topics = KafkaTopicConstants.PAYROLL_PROCESS,  groupId = "payroll-processing-group")
	public void consumePayrollEvent(String message, @Header(KafkaHeaders.RECEIVED_KEY) String userId,  @Header("X-Tenant-ID") String tenantId) {
		log.info("Received Payroll Event: {}", message);

		System.out.println("tenat id "+tenantId +""+TenantContext.getTenantId());
		// Send WebSocket Notification to User
		notificationService.sendNotificationToUser(Long.valueOf(userId), message);
	}

	private void processPayrollEvent(String tenantId, String status) {
		// Process Payroll based on status (INITIATED, IN_PROGRESS, COMPLETED, FAILED)
	}

	private void handleInitiated(String payrollId, String tenantId) {
		log.info("Payroll {} INITIATED for Tenant {}", payrollId, tenantId);
	}

	private void handleInProgress(String payrollId, String tenantId) {
		log.info("Payroll {} IN_PROGRESS for Tenant {}", payrollId, tenantId);
	}

	private void handleCompleted(String payrollId, String tenantId) {
		log.info("Payroll {} COMPLETED for Tenant {}", payrollId, tenantId);
	}

	private void handleFailed(String payrollId, String tenantId) {
		log.error("Payroll {} FAILED for Tenant {}", payrollId, tenantId);
	}
}
