package com.hrms.kafka.service;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

 // Send WebSocket notification to users based on the Kafka topic
    public void sendMessageToTopic(String topicName, List<Long> userIds, String message) {
        for (Long userId : userIds) {
            messagingTemplate.convertAndSend("/topic/" + topicName + "/user-" + userId, message);
        }
    }
}
