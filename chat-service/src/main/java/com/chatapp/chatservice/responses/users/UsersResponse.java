package com.chatapp.chatservice.responses.users;

import com.chatapp.chatservice.data.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsersResponse {
    private List<User> users;
}
