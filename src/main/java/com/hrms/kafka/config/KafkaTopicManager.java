package com.hrms.kafka.config;
import org.apache.kafka.clients.admin.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaTopicManager {

    private final KafkaAdmin kafkaAdmin;
    private final AdminClient adminClient;

    @Value("${kafka.default.partitions:3}")
    private int partitions;

    @Value("${kafka.default.replicas:1}")
    private short replicas;

    public KafkaTopicManager(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
        this.adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    public void createTopicIfNotExists(String topicName) {
        try {
            boolean exists = adminClient.listTopics().names().get().contains(topicName);
            if (!exists) {
                NewTopic newTopic = new NewTopic(topicName, partitions, replicas);
                adminClient.createTopics(Collections.singletonList(newTopic)).all().get();
                System.out.println("Created Kafka topic: " + topicName);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error creating Kafka topic: " + topicName, e);
        }
    }
}
