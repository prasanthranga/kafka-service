/*
 * package com.hrms.kafka.service;
 * 
 * import java.security.Principal;
 * 
 * import org.springframework.context.event.EventListener; import
 * org.springframework.messaging.simp.SimpMessageHeaderAccessor; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.socket.messaging.SessionConnectedEvent;
 * 
 * @Component public class WebSocketEventListener {
 * 
 * private final NotificationService notificationService;
 * 
 * public WebSocketEventListener(NotificationService notificationService) {
 * this.notificationService = notificationService; }
 * 
 * @EventListener public void
 * handleWebSocketConnectListener(SessionConnectedEvent event) { // Get User ID
 * from WebSocket session SimpMessageHeaderAccessor headerAccessor =
 * SimpMessageHeaderAccessor.wrap(event.getMessage()); Principal userPrincipal =
 * headerAccessor.getUser();
 * 
 * if (userPrincipal != null) { Long userId =
 * Long.valueOf(userPrincipal.getName()); // Assuming username is the user ID
 * System.out.println("User Connected: " + userId);
 * 
 * // Send stored notifications
 * notificationService.sendStoredNotifications(userId); } } }
 */