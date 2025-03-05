package com.poc.websocket.HelloWorld;

import lombok.Data;

import java.util.List;

@Data
public class HelloWorld {
    private String message;
    private List<Content> content;

    @Data
    public static class Content {
        private String key;
        private String value;
    }
}
