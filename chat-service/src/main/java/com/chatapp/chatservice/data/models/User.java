package com.chatapp.chatservice.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("users")
public class User {
    @Id
    private String id;
    private String username;
    private String name;
    private String surname;
    private String password;
}