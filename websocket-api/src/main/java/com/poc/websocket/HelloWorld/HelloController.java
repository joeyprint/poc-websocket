package com.poc.websocket.HelloWorld;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RestController
public class HelloController implements WebSocketConfigurer {
    @GetMapping("/")
    public String index() {
        return "Welcome PoC WebSocket";
    }

    @PostMapping("/callback")
    public void getMessage(@RequestBody HelloWorld helloWorld) {
        // send message
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new HelloService(), "/{reportId}/{section}").setAllowedOrigins("*");
    }
}
