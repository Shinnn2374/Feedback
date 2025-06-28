package com.example.FeeedBack.dto;

import com.example.FeeedBack.model.RoleType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserManagerDto {

    @NotNull(message = "User id is definitely")
    private Long userId;

    @NotNull(message = "Role is definitely")
    private RoleType role;
}
