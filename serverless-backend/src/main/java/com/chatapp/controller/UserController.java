package com.chatapp.controller;

import com.chatapp.exception.UserNotFoundException;
import com.chatapp.pojo.User;
import com.chatapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
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

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable("id") String id) {
        userService.deleteUserById(id);
    }
}
