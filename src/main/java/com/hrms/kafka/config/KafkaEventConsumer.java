package com.hrms.kafka.config;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.hrms.kafka.constants.KafkaTopics;

@Service
public class KafkaEventConsumer {
	private final Logger log = LoggerFactory.getLogger(KafkaEventConsumer.class);


    @KafkaListener(topics = "#{kafkaTopicConfig.getAllTopics()}", groupId = "tenant-${tenant.id}")
    public void consumeEvent(@Header("X-Tenant-ID") String tenantId, 
                             @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, 
                             @Payload String payload) {
        log.info("Received event from topic: {} for tenant: {} with payload: {}", topic, tenantId, payload);

        try {
        	 // Process event based on topic dynamically
            if (KafkaTopics.PAYROLL_GENERATED.getTopicName().equals(topic)) {
                processPayrollEvent(tenantId, payload);
            } else if (KafkaTopics.ATTENDANCE_MARKED.getTopicName().equals(topic)) {
                processAttendanceEvent(tenantId, payload);
            } else if (KafkaTopics.LEAVE_REQUESTED.getTopicName().equals(topic)) {
                processLeaveEvent(tenantId, payload);
            } else {
                log.warn("No handler found for topic: {}", topic);
            }
        } catch (Exception e) {
            log.error("Error processing event for topic: {}", topic, e);
        }
    }

    private void processPayrollEvent(String tenantId, String payrollGroupId) {
        log.info("Processing payroll event for tenant: {} and payrollGroupId: {}", tenantId, payrollGroupId);
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
