package com.example.OrderService.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.OrderService.dto.RefreshRequest;
import com.example.OrderService.dto.StatusMessageResponse;
import com.example.OrderService.dto.TokenMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshService {

    private final JWTService jwtService;

    public ResponseEntity<?> refreshTokens(RefreshRequest request) {
        try {
            Long id = Long.valueOf(jwtService.validateToken(request.getRefreshToken()).getSubject());
            String newAccessToken = jwtService.generateAccessToken(id);
            String newRefreshToken = jwtService.generateRefreshToken(id);
            return ResponseEntity.status(200).body(new TokenMessageResponse(200, newAccessToken, newRefreshToken, "Token refreshed successfully"));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(403).body(new StatusMessageResponse(403, "refresh token is invalid"));
        }
    }
}
