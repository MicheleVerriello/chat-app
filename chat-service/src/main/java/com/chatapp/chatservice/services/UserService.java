package com.chatapp.chatservice.services;

import com.chatapp.chatservice.data.models.User;
import com.chatapp.chatservice.exceptions.UserAlreadyExistsException;
import com.chatapp.chatservice.exceptions.UserNotFoundException;
import com.chatapp.chatservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        String username = user.getUsername();
        if (userRepository.findUserByUsernameEqualsIgnoreCase(username).isEmpty()) {
            return userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException("User with username: " + username + " Already Exists");
        }
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsersByUsername(String username) {
        return userRepository.findByUsernameIsLikeIgnoreCase(username);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(String id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User with id: " + id + " Not Found");
        }
    }
}
