package com.chatapp.chatservice.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("User with id: " + id + " Not Found");
    }

    public UserNotFoundException() {
        super("User Not Found");
    }
}
