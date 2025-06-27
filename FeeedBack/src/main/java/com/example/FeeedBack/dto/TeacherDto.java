package com.example.FeeedBack.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDto {

    @NotBlank(message = "Username is definitely")
    private String username;

    @NotBlank(message = "Department is definitely")
    private String department;
}
