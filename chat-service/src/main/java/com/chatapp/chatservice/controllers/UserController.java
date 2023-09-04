package com.chatapp.chatservice.controllers;

import com.chatapp.chatservice.data.models.User;
import com.chatapp.chatservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        this.userService.deleteUser(id);
    }

    @GetMapping
    public List<User> getUsersByUsername(@RequestParam(name = "username") String username) {
        return this.userService.getUsersByUsername(username);
    }
}
