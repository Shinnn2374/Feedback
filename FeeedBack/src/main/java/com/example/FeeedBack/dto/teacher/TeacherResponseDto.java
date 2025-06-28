package com.example.FeeedBack.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherResponseDto {

    private Long id;
    private String username;
    private String department;
    private double avgRating;
}
