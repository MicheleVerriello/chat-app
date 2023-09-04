package com.chatapp.chatservice.repositories;

import com.chatapp.chatservice.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByUsernameIsLikeIgnoreCase(String username);
}
