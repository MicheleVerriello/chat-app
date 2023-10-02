package com.chatapp.chatservice.data.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ChatMessage {

//    private String id;
//    private String fkIdUserSender;
    private String usernameSender;
    private String usernameReceiver;
//    private String fkIdChat;
    private String message;
//    private Date sendDate;
}
