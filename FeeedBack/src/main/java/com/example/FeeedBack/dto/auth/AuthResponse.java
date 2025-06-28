package com.example.FeeedBack.dto.auth;

import com.example.FeeedBack.model.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String email;
    private String username;
    private RoleType role;
}
