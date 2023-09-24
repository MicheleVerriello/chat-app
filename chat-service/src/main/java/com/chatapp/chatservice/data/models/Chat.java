package com.chatapp.chatservice.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "chats")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Chat {
    @Id
    private String id;
    private List<String> componentIds;
    private Date creationDate;
}
