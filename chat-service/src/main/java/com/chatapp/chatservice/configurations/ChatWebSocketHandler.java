package com.chatapp.chatservice.configurations;

import com.chatapp.chatservice.data.models.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> webSocketSessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String username = extractStringFromSession(session);
        session.getAttributes().put("username", username);
        webSocketSessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String senderUsername = extractStringFromSession(session);

        for(WebSocketSession webSocketSession : webSocketSessions){
            String receiverUsername = extractStringFromSession(webSocketSession);

            ObjectMapper mapper = new ObjectMapper();
            ChatMessage chatMessage = mapper.convertValue(message, ChatMessage.class);

            assert receiverUsername != null;
            if (receiverUsername.equals(chatMessage.getUsernameReceiver())) {
                webSocketSession.sendMessage(message);
                System.out.println("Message sent from " + senderUsername + " to " + receiverUsername + ": " + message);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        webSocketSessions.remove(session);
    }

    private String extractStringFromSession(WebSocketSession session) {
        Object usernameAttribute = session.getAttributes().get("username");
        return (usernameAttribute != null) ? usernameAttribute.toString() : null;
    }
}
