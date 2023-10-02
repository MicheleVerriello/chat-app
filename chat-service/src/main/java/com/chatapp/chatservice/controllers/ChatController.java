package com.chatapp.chatservice.controllers;

import com.chatapp.chatservice.configurations.ChatWebSocketHandler;
import com.chatapp.chatservice.data.models.ChatMessage;
import com.chatapp.chatservice.services.SocketSessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

//    private final ChatWebSocketHandler chatWebSocketHandler;

//    @Autowired
//    public ChatController(ChatWebSocketHandler chatWebSocketHandler) {
//        this.chatWebSocketHandler = chatWebSocketHandler;
//    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody ChatMessage chatMessage) {
//        chatWebSocketHandler.handleChatMessage(chatMessage);
    }


//    @MessageMapping("/chats")
//    @SendTo("/topic/chats")
//    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
//
//        System.out.println(
//                    "from: " + chatMessage.getUsernameSender() +
//                            " to: " + chatMessage.getUsernameReceiver() +
//                            " message: " + chatMessage.getMessage());
//
//        return chatMessage;

//        WebSocketSession receiverSession = sessionRegistry.getSession(chatMessage.getUsernameReceiver());
//        System.out.println("WS receiverSession: " + receiverSession.getId());
//
//        if (receiverSession.isOpen()) {
//            System.out.println(
//                    "from: " + chatMessage.getUsernameSender() +
//                            " to: " + chatMessage.getUsernameReceiver() +
//                            " message: " + chatMessage.getMessage());
//
//            messagingTemplate.convertAndSendToUser(
//                    chatMessage.getUsernameReceiver(),
//                    "/queue/messages",
//                    chatMessage.getMessage());
//        }
//    }

//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getFkIdUserSender());
//        return chatMessage;
//    }
}
