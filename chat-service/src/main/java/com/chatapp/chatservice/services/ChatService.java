package com.chatapp.chatservice.services;

import com.chatapp.chatservice.data.models.Chat;
import com.chatapp.chatservice.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public Optional<Chat> findChatByComponentIds(List<String> componentIds) {
        return chatRepository.findChatByComponentIds(componentIds);
    }
}
