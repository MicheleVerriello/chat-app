package com.chatapp.chatservice.data.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "chatmessages")
public class ChatMessage {

    @Id
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFkIdUserSender() {
        return fkIdUserSender;
    }

    public void setFkIdUserSender(String fkIdUserSender) {
        this.fkIdUserSender = fkIdUserSender;
    }

    public String getFkIdUserReceiver() {
        return fkIdUserReceiver;
    }

    public void setFkIdUserReceiver(String fkIdUserReceiver) {
        this.fkIdUserReceiver = fkIdUserReceiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getFkIdChat() {
        return fkIdChat;
    }

    public void setFkIdChat(String fkIdChat) {
        this.fkIdChat = fkIdChat;
    }
}
