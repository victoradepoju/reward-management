package com.victor.rewardmanagement.controller;

import com.victor.rewardmanagement.dto.AuthResponse;
import com.victor.rewardmanagement.dto.LoginDto;
import com.victor.rewardmanagement.dto.RegisterDto;
import com.victor.rewardmanagement.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody @Valid RegisterDto registerDto
    ) {
        try {
            var auth = authenticationService.register(registerDto);
            return ResponseEntity.created(URI.create("/auth/register"))
                    .body(auth);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Registration unsuccessful");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginDto loginDto
    ) {
        try {
            var auth = authenticationService.login(loginDto);
            return ResponseEntity.ok().body(auth);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Authentication failed");
        }
    }
}

