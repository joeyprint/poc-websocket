package com.poc.websocket.WebSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.websocket.HelloWorld.HelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class HelloWebSocketService extends TextWebSocketHandler {
    @Autowired
    private WebSocketService webSocketService;
    Logger logger = LoggerFactory.getLogger(HelloWebSocketService.class);

    public HelloWebSocketService(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        webSocketService.addSession(session.getId(), session);
        logger.info("WebSocket connected: {}", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = new String(message.getPayload());
        logger.info("WebSocket payload: {}", payload);

        try {
            session.sendMessage(new TextMessage(payload));
        } catch (Exception error) {
            logger.error(String.format("Invalid JSON format: %s", error));
            session.sendMessage(new TextMessage("{\"error\": \"Invalid JSON format\"}"));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        webSocketService.removeSession(session.getId());
        logger.info("WebSocket closed: {}", session.getId());
    }
}
