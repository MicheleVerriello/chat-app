package com.chatapp.chatservice.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {

    private String id;
    private String fkIdUserSender;
    private String fkIdUserReceiver;
    private String fkIdChat;
    private String message;
    private Date sendDate;

    public ChatMessage(String fkIdUserSender, String fkIdUserReceiver, String fkIdChat, String message) {
        this.fkIdUserSender = fkIdUserSender;
        this.fkIdUserReceiver = fkIdUserReceiver;
        this.fkIdChat = fkIdChat;
        this.message = message;
        this.sendDate = new Date(System.currentTimeMillis());
    }
}
