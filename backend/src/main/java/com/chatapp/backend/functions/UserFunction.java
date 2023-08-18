package com.chatapp.backend.functions;

import com.chatapp.backend.pojos.User;
import com.chatapp.backend.services.UserService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserFunction implements Function<User, User> {

    private final UserService userService;

    public UserFunction(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User apply(User user) {
        return userService.createUser(user);
    }
}
