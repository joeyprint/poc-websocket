package com.poc.websocket.WebSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.websocket.HelloWorld.HelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class HelloWebSocketService extends TextWebSocketHandler {
    Logger logger = LoggerFactory.getLogger(HelloWebSocketService.class);

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = new String(message.getPayload());
        logger.info("payload {}", payload);

        try {
            // JSON Converter
            ObjectMapper objectMapper = new ObjectMapper();
            HelloWorld helloMessage = objectMapper.readValue(payload, HelloWorld.class);

            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(helloMessage)));
        } catch (Exception error) {
            logger.error(String.format("Invalid JSON format: %s", error));
            session.sendMessage(new TextMessage("{\"error\": \"Invalid JSON format\"}"));
        }
    }
}
