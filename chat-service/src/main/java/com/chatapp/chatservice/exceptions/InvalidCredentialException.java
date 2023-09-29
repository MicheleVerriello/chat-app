package com.chatapp.chatservice.exceptions;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException() {
        super("Username and/or Password not valid");
    }
}
