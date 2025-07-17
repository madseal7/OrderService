package com.example.OrderService.controller;

import com.example.OrderService.model.User;
import com.example.OrderService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/find/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

    @PatchMapping("/update")
    public void updateUser(@RequestBody User updatedUser) {
        userService.updateUser(updatedUser);
    }

    @PostMapping("/create")
    public void createUser(@RequestBody User newUser) {
        userService.createNewUser(newUser);
    }




}
