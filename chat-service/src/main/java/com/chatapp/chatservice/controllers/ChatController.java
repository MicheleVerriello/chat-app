package com.chatapp.chatservice.controllers;

import com.chatapp.chatservice.data.models.Chat;
import com.chatapp.chatservice.data.models.ChatMessage;
import com.chatapp.chatservice.rabbitmq.producers.ChatMessageProducer;
import com.chatapp.chatservice.services.ChatMessageService;
import com.chatapp.chatservice.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/chats")
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

    @PostMapping
    public String sendMessage(@RequestBody ChatMessage chatMessage) {

        if(chatMessage.getFkIdChat() == null) {
            List<String> componentIds = Arrays.asList(chatMessage.getFkIdUserSender(), chatMessage.getFkIdUserReceiver());
            Optional<Chat> chat = this.chatService.findChatByComponentIds(componentIds);

            if (chat.isPresent()) {
                chatMessage.setFkIdChat(chat.get().getId());
            } else {
                Chat newChat = chatService.createChat(new Chat(null, componentIds, new Date(System.currentTimeMillis())));
                chatMessage.setFkIdChat(newChat.getId());
            }
        }
        
        chatMessageService.createChatMessage(chatMessage);

        chatMessageProducer.sendMessage("chat", chatMessage.getMessage());
        return "Message sent to queue: " + chatMessage.getMessage();
    }
}
