package com.chatapp.chatservice.controllers;

import com.chatapp.chatservice.data.models.ChatMessage;
import com.chatapp.chatservice.rabbitmq.producers.ChatMessageProducer;
import com.chatapp.chatservice.services.ChatMessageService;
import com.chatapp.chatservice.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    final ChatMessageProducer chatMessageProducer;
    final ChatMessageService chatMessageService;
    final ChatService chatService;

    @Autowired
    public ChatController(ChatMessageProducer chatMessageProducer, ChatMessageService chatMessageService,
                          ChatService chatService) {
        this.chatMessageProducer = chatMessageProducer;
        this.chatService = chatService;
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getFkIdUserSender());
        return chatMessage;
    }
}
