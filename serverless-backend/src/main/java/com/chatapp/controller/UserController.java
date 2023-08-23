package com.chatapp.controller;

import com.chatapp.exception.UserNotFoundException;
import com.chatapp.pojo.User;
import com.chatapp.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "User") //add user to cache if this method is called more than once
    public User getUserById(@PathVariable("id") String id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "users", key="#id") //remove user from cache
    public void deleteUser(@PathVariable("id") String id) {
        userService.deleteUserById(id);
    }
}
