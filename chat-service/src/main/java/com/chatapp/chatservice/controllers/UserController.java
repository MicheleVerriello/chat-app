package com.chatapp.chatservice.controllers;

import com.chatapp.chatservice.data.models.User;
import com.chatapp.chatservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(value = "users", allEntries = true)
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "users") // Remove user from cache
    public void deleteUser(@PathVariable("id") String id) {
        this.userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @CachePut(key = "#id", value = "users")
    public User updateUser(@PathVariable("id") String id, @RequestBody User user) {
        user.setId(id);
        return this.userService.updateUser(user);
    }

    @Cacheable(key = "#username", value = "users") // Cache users by username
    @GetMapping
    public List<User> getUsersByUsername(@RequestParam(name = "username") String username) {
        return this.userService.getUsersByUsername(username);
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "users") // Cache user
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }
}