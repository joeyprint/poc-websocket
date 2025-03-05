package com.poc.websocket.HelloWorld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
public class HelloWorld {
    @JsonProperty("message")
    private String message;
    @JsonProperty("contents")
    private List<String> contents;
}
