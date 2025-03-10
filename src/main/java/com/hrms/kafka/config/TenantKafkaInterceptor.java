package com.hrms.kafka.config;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;

public class TenantKafkaInterceptor implements ConsumerInterceptor<String, String> {

    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        for (ConsumerRecord<String, String> record : records) {
            Headers headers = record.headers();
            Header tenantHeader = headers.lastHeader("tenant-id");
            if (tenantHeader != null) {
                String tenantId = new String(tenantHeader.value(), StandardCharsets.UTF_8);
                TenantContext.setTenantId(tenantId); // ThreadLocal storage
            }
        }
        return records;
    }

    @Override
    public void close() {}

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {}

    @Override
    public void configure(Map<String, ?> configs) {}
}
