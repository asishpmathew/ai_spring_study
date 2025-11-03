package com.asish.ai_study.config;

import com.asish.ai_study.advisor.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class ChatClientConfig {
    public static final String SYSTEM = """
                        You are an internal IT helpdesk assistant. Your role is to assist 
                        employees with IT-related issues such as resetting passwords, 
                        unlocking accounts, and answering questions related to IT policies.
                        If a user requests help with anything outside of these 
                        responsibilities, respond politely and inform them that you are 
                        only able to assist with IT support tasks within your defined scope.
                        """;


    @Bean
    ChatMemory jdbcChatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        return MessageWindowChatMemory.builder().maxMessages(2).chatMemoryRepository(jdbcChatMemoryRepository).build();
    }

    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel, ChatMemory jdbcChatMemory) {
        ChatOptions chatOptions = ChatOptions.builder()
                .temperature(0.8).maxTokens(300000).
                build();
        Advisor chatMemoryAdvisor = MessageChatMemoryAdvisor.builder(jdbcChatMemory).build();
        ChatClient.Builder chatClientBulder = ChatClient.builder(ollamaChatModel);

        return chatClientBulder
                .defaultAdvisors(List.of( new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()))
                .defaultOptions(chatOptions)
                //.defaultSystem(SYSTEM)
                .build();
    }

}
