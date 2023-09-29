package com.chatapp.chatservice.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("User with username: " + username + " Already Exists");
    }
}
