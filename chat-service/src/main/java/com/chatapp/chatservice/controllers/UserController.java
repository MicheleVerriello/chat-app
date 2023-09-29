package com.chatapp.chatservice.controllers;

import com.chatapp.chatservice.data.models.User;
import com.chatapp.chatservice.responses.login.LoginResponse;
import com.chatapp.chatservice.responses.users.UsersResponse;
import com.chatapp.chatservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<LoginResponse> createUser(@RequestBody User user) {
        User addedUser = this.userService.createUser(user);

        return ResponseEntity.ok(new LoginResponse(addedUser.getId()));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "users") // Remove user from cache
    public void deleteUser(@PathVariable("id") String id) {
        this.userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @CachePut(key = "#id", value = "users")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        user.setId(id);
        User updatedUser = this.userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @Cacheable(key = "#username", value = "users") // Cache users by username
    @GetMapping
    public ResponseEntity<UsersResponse> getUsersByUsername(@RequestParam(name = "username") String username) {
        UsersResponse response = new UsersResponse();
        List<User> users = this.userService.getUsersByUsername(username);
        response.setUsers(users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "users") // Cache user
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}