package com.chatapp.controller;

import com.chatapp.backend.exceptions.UserNotFoundException;
import com.chatapp.backend.pojos.User;
import com.chatapp.backend.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api.version-path}/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable("id") String id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }

    @PostMapping
    User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
