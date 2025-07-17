package com.example.OrderService.controller;

import com.example.OrderService.dto.*;
import com.example.OrderService.service.LoginService;
import com.example.OrderService.service.RefreshService;
import com.example.OrderService.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final RegisterService registerService;
    private final LoginService loginService;
    private final RefreshService refreshService;

    @PostMapping("/reg")
    public ResponseEntity<StatusMessageResponse> regNewUser(@RequestBody RegisterRequest request) {
        return registerService.registerNewUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        return loginService.loginUser(request);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> RefreshToken(@RequestBody RefreshRequest request) {
        return refreshService.refreshTokens(request);
    }

}
