package com.chatapp.chatservice.repositories;

import com.chatapp.chatservice.data.models.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {

    Optional<Chat> findChatByComponentIds(List<String> componentIds);
}
