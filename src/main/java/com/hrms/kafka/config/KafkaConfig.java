package com.hrms.kafka.config;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import com.hrms.kafka.constants.KafkaTopics;

@Configuration
public class KafkaConfig {

	@Value("${kafka.topics.hrms.partitions}")
	private int partitions;

	@Value("${kafka.topics.hrms.replicas}")
	private short replicas;

	 @Bean
	    public KafkaAdmin.NewTopics createTopics() {
	        List<NewTopic> topics = List.of(KafkaTopics.values()).stream()
	                .map(topic -> TopicBuilder.name(topic.getTopicName())
	                        .partitions(partitions)
	                        .replicas(replicas)
	                        .config(TopicConfig.RETENTION_MS_CONFIG, "604800000") // 7 days retention
	                        .build())
	                .collect(Collectors.toList());

	        return new KafkaAdmin.NewTopics(topics.toArray(new NewTopic[0]));
	    }
}
