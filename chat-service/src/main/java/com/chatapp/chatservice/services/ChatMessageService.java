package com.chatapp.chatservice.services;

import com.chatapp.chatservice.data.models.ChatMessage;
import com.chatapp.chatservice.repositories.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessage createChatMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll();
    }

    public Optional<ChatMessage> getMessageById(String id) {
        return chatMessageRepository.findById(id);
    }

    public ChatMessage updateMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    public void deleteMessageById(String id) {
        chatMessageRepository.deleteById(id);
    }
}
