package com.example.FeeedBack.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedBackRequest {

    @NotNull(message = "Id of teacher is definitely")
    private Long teacherId;

    @Min(value = 1, message = "The grade min value is 1")
    @Max(value = 5, message = "The grade max value is 5")
    private int knowledgeRating;

    @Min(1)
    @Max(5)
    private int communicationRating;

    @Min(1)
    @Max(5)
    private int organizationRating;

    @Size(max = 1000)
    private String comment;
}
