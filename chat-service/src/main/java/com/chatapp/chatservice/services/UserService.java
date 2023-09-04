package com.chatapp.chatservice.services;

import com.chatapp.chatservice.data.models.User;
import com.chatapp.chatservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsersByUsername(String username) {
        return userRepository.findByUsernameIsLikeIgnoreCase(username);
    }
}
