package com.chatapp.chatservice.services;

import com.chatapp.chatservice.data.models.User;
import com.chatapp.chatservice.exceptions.*;
import com.chatapp.chatservice.repositories.UserRepository;
import com.chatapp.chatservice.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) throws HashingException, InvalidPasswordException, UserAlreadyExistsException {
        String username = user.getUsername();
        String password = user.getPassword();

        if (isValid(username) && isValid(password)) {
            Optional<User> userOptional = userRepository.findUserByUsernameEqualsIgnoreCase(username);
            if (userOptional.isEmpty()) {
                try {
                    user.setPassword(Security.hashString(password));
                } catch (NoSuchAlgorithmException e) {
                    throw new HashingException();
                }
            } else {
                throw new UserAlreadyExistsException(username);
            }
            return userRepository.save(user);
        } else {
            throw new InvalidCredentialException();
        }
    }

    public void deleteUser(String id) {
        getUserById(id);
        userRepository.deleteById(id);
    }

    public List<User> getUsersByUsername(String username) {
        return userRepository.findByUsernameIsLikeIgnoreCase(username);
    }

    public User updateUser(User user) {
        getUserById(user.getId());
        return userRepository.save(user);
    }

    public User getUserById(String id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException(id);
        }
    }

    public String login(String username, String password) {
        Optional<User> user;
        String id;
        try {
            if (isValid(username) && isValid(password)) {
                user = userRepository.findUserByUsernameAndPassword(username, Security.hashString(password));
            } else {
                throw new InvalidCredentialException();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new HashingException();
        }

        if (user.isPresent()) {
            id = user.get().getId();
        } else {
            throw new UserNotFoundException();
        }

        return id;
    }

    private boolean isValid(String s) {
        return s != null && !s.isEmpty() && !s.isBlank();
    }
}
