package com.chatapp.chatservice.data.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "chats")
public class Chat {
    @Id
    private String id;
    private List<String> componentIds;
    private Date creationDate;

    public Chat(String id, List<String> componentIds, Date creationDate) {
        this.id = id;
        this.componentIds = componentIds;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getComponents() {
        return componentIds;
    }

    public void setComponents(List<String> components) {
        this.componentIds = components;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}