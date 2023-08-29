package com.chatapp.chatservice.repositories;

import com.chatapp.chatservice.data.models.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}
