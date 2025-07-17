package com.example.OrderService.service;

import com.example.OrderService.dto.RegisterRequest;
import com.example.OrderService.dto.StatusMessageResponse;
import com.example.OrderService.model.User;
import com.example.OrderService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository repository;

    public boolean isUserAlreadyRegisteredByEmail (RegisterRequest request) {
        return repository.existsByEmail(request.getEmail());
    }

    public boolean isUserAlreadyRegisteredByUsername (RegisterRequest request) {
        return repository.existsByUsername(request.getUsername());
    }

    public ResponseEntity<StatusMessageResponse> registerNewUser(RegisterRequest request) {

        if (isUserAlreadyRegisteredByEmail(request)) {
            return ResponseEntity.status(409).body(new StatusMessageResponse(409, "User with this email already exists"));
        }

        if (isUserAlreadyRegisteredByUsername(request)) {
            return ResponseEntity.status(409).body(new StatusMessageResponse(409, "User with this username already exists"));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("USER");
        repository.save(user);

        return ResponseEntity.status(200).body(new StatusMessageResponse(200, "new user successfully registered"));
    }
}
