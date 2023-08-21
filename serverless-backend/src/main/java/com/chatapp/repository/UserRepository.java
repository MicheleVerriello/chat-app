package com.chatapp.repository;

import com.chatapp.backend.pojos.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
