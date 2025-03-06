package com.poc.websocket.HelloWorld;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.websocket.WebSocket.HelloWebSocketConfig;
import com.poc.websocket.WebSocket.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    private final WebSocketService webSocketService;
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    public HelloController(HelloWebSocketConfig helloWebSocketConfig, WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @GetMapping("/")
    public String index() {
        return "Welcome PoC WebSocket";
    }

    @PostMapping("/callback")
    public ResponseEntity getMessage(@RequestBody HelloWorld helloWorld) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String message = objectMapper.writeValueAsString(helloWorld);
            logger.info("Received message for WebSocket: {}", message);
            webSocketService.sendMessageToAll(message);

            return ResponseEntity.status(HttpStatus.OK).body("Callback success");
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
    }
}
