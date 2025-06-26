package com.example.FeeedBack.controller;

import com.example.FeeedBack.exception.AlreadyExistException;
import com.example.FeeedBack.model.AuthResponse;
import com.example.FeeedBack.model.CreateUserRequest;
import com.example.FeeedBack.model.LoginRequest;
import com.example.FeeedBack.model.SimpleResponse;
import com.example.FeeedBack.repository.UserRepository;
import com.example.FeeedBack.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    private final SecurityService securityService;

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> authUser(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(securityService.authenticateUser(request));
    }

    @PostMapping("/register")
    public ResponseEntity<SimpleResponse> registerUser(@RequestBody CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AlreadyExistException("Username is already in exist");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistException("Email is already in exist");
        }

        securityService.register(request);
        return ResponseEntity.ok(new SimpleResponse("User created"));
    }
}
