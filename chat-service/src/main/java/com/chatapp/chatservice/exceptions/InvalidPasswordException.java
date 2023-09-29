package com.chatapp.chatservice.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Password not valid");
    }
}
