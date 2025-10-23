package com.asish.ai_study.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openai")
public class ChatControllerOpenAi {

    private final ChatClient ollamaChatClient;

    public ChatControllerOpenAi( ChatClient ollamaChatClient){
        this.ollamaChatClient = ollamaChatClient;
    }

    @GetMapping("/chat")
    public String chatResponder(@RequestParam("message") String message) {
        return ollamaChatClient.prompt(message).call().content();
    }
}
