package com.example.FeeedBack.controller;

import com.example.FeeedBack.dto.user.UserProfileDto;
import com.example.FeeedBack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getMyProfile(
            Authentication authentication
    ) {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.getUserProfile(email));
    }
}