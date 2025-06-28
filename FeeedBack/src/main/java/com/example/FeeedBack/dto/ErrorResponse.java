package com.example.FeeedBack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private int statuis;

    private String message;

    private String timestamp; // LocalDateTime.now().toString()
}
