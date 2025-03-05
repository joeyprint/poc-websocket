package com.poc.websocket.HelloWorld;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController  {
    @GetMapping("/")
    public String index() {
        return "Welcome PoC WebSocket";
    }

    @PostMapping("/callback")
    public void getMessage(@RequestBody HelloWorld helloWorld) {
        // send message
    }
}
