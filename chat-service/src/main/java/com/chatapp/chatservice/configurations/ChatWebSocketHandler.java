package com.chatapp.chatservice.configurations;

import com.chatapp.chatservice.data.models.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> webSocketSessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("afterConnectionEstablished");
        System.out.println("sessionId: " + session.getId());
        System.out.println("session attributes: " + session.getAttributes());


        String username = extractStringFromSession(session);
        System.out.println("session username: " + username);
        session.getAttributes().put("username", username);
        webSocketSessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("handleTextMessage");
        String senderUsername = extractStringFromSession(session);
        System.out.println("senderUsername: " + senderUsername);
        System.out.println("message: " + message.getPayload());

        for(WebSocketSession webSocketSession : webSocketSessions){
            String receiverUsername = extractStringFromSession(webSocketSession);

            ObjectMapper mapper = new ObjectMapper();
            ChatMessage chatMessage = mapper.readValue(message.getPayload(), ChatMessage.class);


            if (receiverUsername != null && receiverUsername.equals(chatMessage.getUsernameReceiver())) {
                webSocketSession.sendMessage(message);
                System.out.println("Message sent from " + senderUsername + " to " + receiverUsername + ": " + chatMessage.getMessage());
            } else {
                // add message to queue
                System.out.println("Message not sent from " + senderUsername + " to " + receiverUsername + ": " + chatMessage.getMessage());
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("afterConnectionClosed: " + session.getHandshakeHeaders().get("username").get(0));
        webSocketSessions.remove(session);
    }

    private String extractStringFromSession(WebSocketSession session) {

        HttpHeaders headers = session.getHandshakeHeaders();
        List<String> usernameHeaders = headers.get("username");

        if(usernameHeaders != null && !usernameHeaders.isEmpty()) {
            return usernameHeaders.get(0);
        } else {
            return null;
        }
    }
}
