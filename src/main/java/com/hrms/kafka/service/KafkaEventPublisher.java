package com.hrms.kafka.service;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.hrms.kafka.config.KafkaTopicManager;
import com.hrms.kafka.constants.KafkaTopics;

@Service
public class KafkaEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTopicManager kafkaTopicManager;

    public KafkaEventPublisher(KafkaTemplate<String, String> kafkaTemplate, KafkaTopicManager kafkaTopicManager) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicManager = kafkaTopicManager;
    }

    public void publishEvent(KafkaTopics topic, String tenantId, String key, String event) {
        String topicName = topic.getTopicName();

        // Ensure the topic exists before publishing
        kafkaTopicManager.createTopicIfNotExists(topicName);

        // Add tenant ID to headers
        Message<String> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .setHeader("X-Tenant-ID", tenantId)  // Tenant ID added as a header
                .setHeader(KafkaHeaders.KEY, key)   // Partition key
                .build();

        kafkaTemplate.send(message);
        System.out.println("Published event to topic: " + topicName);
    }
}
