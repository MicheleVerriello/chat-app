package com.chatapp.chatservice.data.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "chats")
public record Chat (@Id String id, List<String> componentIds, Date creationDate) {}
