package com.example.FeeedBack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedBackResponse {

    private Long id;

    private String studentName;

    private String teacherName;

    private int knowledgeRating;

    private int communicationRating;

    private int organizationRating;

    private String comment;

    private LocalDateTime createdAt;
}
