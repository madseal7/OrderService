package com.example.OrderService.service;

import com.example.OrderService.dto.LoginRequest;
import com.example.OrderService.dto.StatusMessageResponse;
import com.example.OrderService.dto.TokenMessageResponse;
import com.example.OrderService.model.User;
import com.example.OrderService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JWTService jwtService;
    private final PasswordEncoder encoder;
    private final UserRepository repository;

    public ResponseEntity<?> loginUser(LoginRequest request) {
        Optional<User> optionalUser = repository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body(new StatusMessageResponse(404, "User not found"));
        }

        User user = optionalUser.get();

        if (encoder.matches(request.getPassword(), user.getPassword())) {
            String accessToken = jwtService.generateAccessToken(user.getId());
            String refreshToken = jwtService.generateRefreshToken(user.getId());
            return ResponseEntity.status(200).body(new TokenMessageResponse(200, accessToken, refreshToken, "Login successfully"));
        } else {
            return ResponseEntity.status(403).body(new StatusMessageResponse(403, "Access denied"));
        }
    }
}
