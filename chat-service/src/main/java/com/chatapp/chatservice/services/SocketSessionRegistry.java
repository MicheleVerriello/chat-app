package com.chatapp.chatservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SocketSessionRegistry {

    private final Map<String, WebSocketSession> users = new ConcurrentHashMap<>();

    public void register(String username, WebSocketSession session) {
        users.put(username, session);
    }

    public void deregister(String username) {
        users.remove(username);
    }

    public WebSocketSession getSession(String username) {
        return users.get(username);
    }

}
