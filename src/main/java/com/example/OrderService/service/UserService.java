package com.example.OrderService.service;

import com.example.OrderService.model.User;
import com.example.OrderService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Optional<User> getUserById(Long userId) {
        return repository.findById(userId);
    }

    public void deleteUserById(Long userId) {
        repository.deleteById(userId);
    }

    public void createNewUser(User user) {
        repository.save(user);
    }

    public void updateUser(User updatedUser) {
        repository.save(updatedUser);
    }
}
