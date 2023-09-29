package com.chatapp.chatservice.controllers;

import com.chatapp.chatservice.data.models.User;
import com.chatapp.chatservice.responses.login.LoginResponse;
import com.chatapp.chatservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authorize")
public class AuthorizeController {

    private final UserService userService;

    @Autowired
    public AuthorizeController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User user) {

        String id = userService.login(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(new LoginResponse(id));
    }
}
