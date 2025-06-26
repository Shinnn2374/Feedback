package com.example.FeeedBack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

    private Long id;

    private String refreshToken;

    private String username;

    private String email;

    private List<String> roles;
}
