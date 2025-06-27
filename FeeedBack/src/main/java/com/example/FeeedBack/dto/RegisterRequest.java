package com.example.FeeedBack.dto;

import com.example.FeeedBack.model.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Email is definitely")
    @Email(message = "impolite email")
    private String email;

    @NotBlank(message = "Password is definitely")
    @Size(message = "The password must consist of at least 6 characters")
    private String password;

    @NotBlank(message = "Full name is definitely")
    private String username;

    @NotNull(message = "Role is not found")
    private RoleType role;
}
