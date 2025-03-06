package com.poc.websocket.WebSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WebSocketService {
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    public void addSession(String sessionId, WebSocketSession session) {
        sessions.put(sessionId, session);
        logger.info("Session added: {}", sessionId);
    }

    public void removeSession(String sessionId) {
        sessions.remove(sessionId);
        logger.info("Session removed: {}", sessionId);
    }

    public void sendMessageToAll(String message) throws IOException {
        logger.info("Broadcasting message to all {} sessions", sessions.size());
        for (WebSocketSession session : sessions.values()) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }
}
