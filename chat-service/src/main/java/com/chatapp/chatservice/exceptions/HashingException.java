package com.chatapp.chatservice.exceptions;

public class HashingException extends RuntimeException {
    public HashingException() {
        super("Generic error on hashing password");
    }
}
