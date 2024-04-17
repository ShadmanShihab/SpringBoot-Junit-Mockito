package com.app.springbootjunit5mockito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class ChatBotResponse {
    private List<Choice> choices;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {
        private int index;
        private Message message;
    }
}
